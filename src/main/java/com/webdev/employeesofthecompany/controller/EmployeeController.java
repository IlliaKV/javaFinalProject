package com.webdev.employeesofthecompany.controller;

import com.webdev.employeesofthecompany.domain.Department;
import com.webdev.employeesofthecompany.domain.Employee;
import com.webdev.employeesofthecompany.domain.Position;
import com.webdev.employeesofthecompany.domain.Status;
import com.webdev.employeesofthecompany.service.jpa.DepartmentService;
import com.webdev.employeesofthecompany.service.jpa.EmployeeService;
import com.webdev.employeesofthecompany.service.jpa.PositionService;
import com.webdev.employeesofthecompany.service.jpa.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController extends BaseSecurityController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private PositionService positionService;

    @GetMapping("/moder/employees/create")
    public ModelAndView createEmployee() {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/employees/create");
        modelAndView.addObject("departments", departmentService.getAll());
        modelAndView.addObject("statuses", statusService.getAll());
        modelAndView.addObject("positions", positionService.getAll());
        return modelAndView;
    }

    @PostMapping("/moder/employees/create")
    public String createEmployee(@ModelAttribute Employee employee,
                                 @ModelAttribute Department department,
                                 @ModelAttribute Status status,
                                 @ModelAttribute Position position) {
        employee.setDepartment(department);
        employee.setStatus(status);
        employee.setPosition(position);
        employeeService.save(employee);
        return "redirect:/moder/employees/employees";
    }

    @GetMapping("/moder/employees/edit")
    public ModelAndView editEmployeeModer(@RequestParam long employeeId) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/employees/edit");

        modelAndView.addObject("employee", employeeService.getById(employeeId));
        modelAndView.addObject("departments", departmentService.getAll());
        modelAndView.addObject("statuses", statusService.getAll());
        modelAndView.addObject("positions", positionService.getAll());

        return modelAndView;
    }

    @PostMapping("/moder/employees/edit")
    public String editEmployeeModer(@RequestParam(value = "employeeId", required = true) long employeeId,
                                   @RequestParam(value = "firstName", required = false) String firstName,
                                   @RequestParam(value = "lastName", required = false) String lastName,
                                   @RequestParam(value = "phone", required = false) String phone,
                                    @RequestParam(value = "email", required = false) String email,
                                   @RequestParam(value = "department", required = false) Department department,
                                    @RequestParam(value = "status", required = false) Status status,
                                    @RequestParam(value = "position", required = false) Position position) {

        Employee employee = employeeService.getById(employeeId);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPhone(phone);
        employee.setEmail(email);
        employee.setDepartment(department);
        employee.setStatus(status);
        employee.setPosition(position);

        employeeService.update(employee);
        return "redirect:/moder/employees/employees";
    }


    @GetMapping("/moder/employees/delete")
    public String deleteEmployeeById(@RequestParam long employeeId) {
        if (employeeService.exists(employeeId)) {
            Employee employee = employeeService.getById(employeeId);
            employeeService.delete(employee);
        }
        return "redirect:/moder/employees/employees";
    }

    @GetMapping("/moder/employees/employees")
    public ModelAndView getEmployees(@RequestParam(required = false, defaultValue = "4") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/employees/employees");
        modelAndView.addObject("employees", employeeService.getAll());
        return modelAndView;
    }


    @GetMapping("/employees/employee")
    public ModelAndView getEmploee(@RequestParam(required = false, defaultValue = "42") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("employees/employee");
        modelAndView.addObject("employee", getCurrentUser());
        return modelAndView;
    }

    private Employee getCurrentUser() {
        String userEmail = securityProcessor.getCurrentUserEmail();
        return employeeService.getByEmail(userEmail);
    }
}
