package com.webdev.employeesofthecompany.controller;

import com.webdev.employeesofthecompany.domain.Department;
import com.webdev.employeesofthecompany.domain.Employee;
import com.webdev.employeesofthecompany.service.jpa.DepartmentService;
import com.webdev.employeesofthecompany.service.jpa.EmployeeService;
import com.webdev.employeesofthecompany.service.jpa.PositionService;
import com.webdev.employeesofthecompany.service.jpa.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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