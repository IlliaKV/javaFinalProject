package com.webdev.employeesofthecompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController extends BaseSecurityController{

    @GetMapping("/")
    public ModelAndView index(){
        return modelAndViewSecurityBase("index");
    }

    @GetMapping("/moder/index")
    public ModelAndView indexModer(){
        return modelAndViewSecurityBase("moder/index");
    }

    @GetMapping("/admin/index")
    public ModelAndView indexAdmin(){
        return modelAndViewSecurityBase("admin/index");
    }
}
