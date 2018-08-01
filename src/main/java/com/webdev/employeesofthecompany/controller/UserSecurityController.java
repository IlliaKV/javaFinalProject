package com.webdev.employeesofthecompany.controller;

import com.webdev.employeesofthecompany.domain.Employee;
import com.webdev.employeesofthecompany.domain.Role;
import com.webdev.employeesofthecompany.service.jpa.EmployeeService;
import com.webdev.employeesofthecompany.service.jpa.RoleService;
import com.webdev.employeesofthecompany.service.security.EmployeeValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserSecurityController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeValidateService employeeValidateService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute Employee employee) {
        EmployeeValidateService.ValidateResult result = employeeValidateService.validate(employee);

        if (result == EmployeeValidateService.ValidateResult.ok) {
            employee.setActive(1);
            Role userRole = roleService.getRoleByName("EMPLO");
            employee.getRoles().add(userRole);
            employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
            employeeService.save(employee);
            return new ModelAndView("redirect:/login");
        } else {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("error", result.getStringRepresenation());
            return modelAndView;
        }
    }
}
