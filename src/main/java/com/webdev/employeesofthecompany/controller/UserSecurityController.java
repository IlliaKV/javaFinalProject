package com.webdev.employeesofthecompany.controller;

import com.webdev.employeesofthecompany.service.jpa.EmployeeService;
import com.webdev.employeesofthecompany.service.security.EmployeeValidateService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

//    @PostMapping("/register")
//    public ModelAndView register(@ModelAttribute User user) {
//        UserValidateService.ValidateResult result = userValidateService.validate(user);
//
//        if (result == UserValidateService.ValidateResult.ok) {
//            user.setActive(1);
//            userService.addNewUser(user);
//            return new ModelAndView("redirect:/login");
//        } else {
//            ModelAndView modelAndView = new ModelAndView("register");
//            modelAndView.addObject("error", result.getStringRepresenation());
//            return modelAndView;
//        }
//    }
}
