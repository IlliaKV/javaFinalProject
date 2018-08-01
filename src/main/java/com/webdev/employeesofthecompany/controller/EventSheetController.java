package com.webdev.employeesofthecompany.controller;


import com.sun.org.apache.xerces.internal.xs.StringList;
import com.webdev.employeesofthecompany.domain.Employee;
import com.webdev.employeesofthecompany.domain.EventSheet;
import com.webdev.employeesofthecompany.service.jpa.EmployeeService;
import com.webdev.employeesofthecompany.service.jpa.EventService;
import com.webdev.employeesofthecompany.service.jpa.EventSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EventSheetController extends BaseSecurityController{

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EventSheetService eventSheetService;
    @Autowired
    private EventService eventService;

    @GetMapping("/employees/eventssheet")
    public ModelAndView geеEventSheetsOfEmployee(@RequestParam(required = false, defaultValue = "42") String value) {

//        List<String> eventList = new ArrayList<>();
        for (EventSheet es: getCurrentUser().getEventSheets()) {
//            eventList.add(eventService.getById(es.getEvent().getId()).getNameEvent());
            System.out.println(es.getEvent().getNameEvent());
        }

        ModelAndView modelAndView = modelAndViewSecurityBase("employees/eventssheet");

        modelAndView.addObject("employee", getCurrentUser());
        modelAndView.addObject("eventSheets", getCurrentUser().getEventSheets());
//        modelAndView.addObject("eventNames", eventList);

        return modelAndView;
    }

    private Employee getCurrentUser() {
        String userEmail = securityProcessor.getCurrentUserEmail();
        return employeeService.getByEmail(userEmail);
    }
}
