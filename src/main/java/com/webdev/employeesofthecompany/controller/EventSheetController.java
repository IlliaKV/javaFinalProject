package com.webdev.employeesofthecompany.controller;


import com.webdev.employeesofthecompany.domain.Employee;
import com.webdev.employeesofthecompany.domain.Event;
import com.webdev.employeesofthecompany.domain.EventSheet;
import com.webdev.employeesofthecompany.service.jpa.EmployeeService;
import com.webdev.employeesofthecompany.service.jpa.EventService;
import com.webdev.employeesofthecompany.service.jpa.EventSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

import static com.webdev.employeesofthecompany.service.ParseDateService.parseDateToStringYyyyMmDdTHhMm;

@Controller
public class EventSheetController extends BaseSecurityController{

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EventSheetService eventSheetService;

    @Autowired
    private EventService eventService;

    @GetMapping("/admin/employees/eventssheet")
    public ModelAndView getEventSheetsOfEmployeeAdmin(@RequestParam long employeeId) {
        ModelAndView modelAndView = modelAndViewSecurityBase("admin/employees/eventssheet");
        Employee employee = employeeService.getById(employeeId);
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("eventSheets", employee.getEventSheets());
        return modelAndView;
    }

    @GetMapping("/moder/events/create")
    public ModelAndView createEventSheets() {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/events/create");
        modelAndView.addObject("events", eventService.getAll());
        modelAndView.addObject("Allemployees", employeeService.getAll());
        modelAndView.addObject("hours", new String[eventService.getAll().size()+1]);
        return modelAndView;
    }

    @PostMapping("/moder/events/create")
    public String createEventSheets(@RequestParam(value = "Allemployees") String[] employees,
                                 @ModelAttribute(value = "event") Event event,
                                 @ModelAttribute(value = "dateStart") String dateStart,
                                 @RequestParam(value = "hour") String[] hours) {
        for(int i = 0; i < employees.length; i++){
            Employee employee = employeeService.getById(Long.parseLong(employees[i]));
            EventSheet eventSheet = new EventSheet();
            eventSheet.setEvent(event);
            eventSheet.setEmployee(employee);
            eventSheet.setNumberOfHours(Integer.parseInt(hours[i]));
            try {
                eventSheet.setDateStart(parseDateToStringYyyyMmDdTHhMm(dateStart));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            eventSheetService.save(eventSheet);
        }
        return "redirect:/moder/events/events";
    }

    @GetMapping("/moder/events/edit")
    public ModelAndView editEventSheetModer(@RequestParam long eventSheetId) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/events/edit");
        modelAndView.addObject("eventSheet", eventSheetService.getById(eventSheetId));
        modelAndView.addObject("employees", employeeService.getAll());
        modelAndView.addObject("events", eventService.getAll());
        return modelAndView;
    }

    @PostMapping("/moder/events/edit")
    public String editEventSheetModer(@RequestParam(value = "eventSheetId", required = true) long eventSheetId,
                                    @RequestParam(value = "dateStart", required = false) String dateStart,
                                    @RequestParam(value = "numberOfHours", required = false) int numberOfHours,
                                    @RequestParam(value = "employee", required = false) Employee employee,
                                    @RequestParam(value = "event", required = false) Event event) {
        System.out.println(dateStart);
        EventSheet eventSheet = eventSheetService.getById(eventSheetId);
        try {
            eventSheet.setDateStart(parseDateToStringYyyyMmDdTHhMm(dateStart));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        eventSheet.setNumberOfHours(numberOfHours);
        eventSheet.setEmployee(employee);
        eventSheet.setEvent(event);
        eventSheetService.update(eventSheet);
        return "redirect:/moder/events/events";
    }

    @GetMapping("/moder/events/delete")
    public String deleteEmployeeById(@RequestParam long eventSheetId) {
        if (eventSheetService.exists(eventSheetId)) {
            EventSheet eventSheet = eventSheetService.getById(eventSheetId);
            eventSheetService.delete(eventSheet);
        }
        return "redirect:/moder/events/events";
    }

    @GetMapping("/moder/events/events")
    public ModelAndView getEventSheets(@RequestParam(required = false, defaultValue = "42") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/events/events");
        modelAndView.addObject("events", eventService.getAll());
        return modelAndView;
    }

    @GetMapping("/moder/employees/eventssheet")
    public ModelAndView getEventSheetsOfEmployeeModer(@RequestParam long employeeId) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/employees/eventssheet");
        Employee employee = employeeService.getById(employeeId);
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("eventSheets", employee.getEventSheets());
        return modelAndView;
    }

    @GetMapping("/employees/eventssheet")
    public ModelAndView getEventSheetsOfEmployee(@RequestParam(required = false, defaultValue = "42") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("employees/eventssheet");
        modelAndView.addObject("employee", getCurrentUser());
        modelAndView.addObject("eventSheets", getCurrentUser().getEventSheets());
        return modelAndView;
    }

    private Employee getCurrentUser() {
        String userEmail = securityProcessor.getCurrentUserEmail();
        return employeeService.getByEmail(userEmail);
    }
}
