package com.webdev.employeesofthecompany.controller;

import com.webdev.employeesofthecompany.domain.Employee;
import com.webdev.employeesofthecompany.service.jpa.EmployeeService;
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

    @GetMapping("/employee/{id}")
    public ModelAndView getEmploee(@PathVariable String id) {
        Employee employee = getCurrentUser();

        ModelAndView modelAndView = modelAndViewSecurityBase("employee/" + employee.getId());

        modelAndView.addObject("employee", employee);

        return modelAndView;
    }

    private Employee getCurrentUser() {
        String userEmail = securityProcessor.getCurrentUserEmail();
        return employeeService.getByEmail(userEmail);
    }
}
