package com.webdev.employeesofthecompany.controller;

import com.webdev.employeesofthecompany.service.security.SecurityProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseSecurityController {
    @Autowired
    protected SecurityProcessor securityProcessor;

    public ModelAndView modelAndViewSecurityBase(String viewName) {
        return securityProcessor.modelAndViewSecurity(viewName);
    }
}