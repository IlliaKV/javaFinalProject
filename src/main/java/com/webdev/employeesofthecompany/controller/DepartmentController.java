package com.webdev.employeesofthecompany.controller;

import com.webdev.employeesofthecompany.domain.Department;
import com.webdev.employeesofthecompany.service.jpa.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DepartmentController extends BaseSecurityController{

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/moder/departments/create")
    public ModelAndView createDepartment() {
        return modelAndViewSecurityBase("moder/departments/create");
    }

    @PostMapping("/moder/departments/create")
    public String createDepartment(@ModelAttribute Department department) {
        departmentService.save(department);
        return "redirect:/moder/departments/departments";
    }

    @GetMapping("/moder/departments/edit")
    public ModelAndView editDepartmentModer(@RequestParam long departmentId) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/departments/edit");
        modelAndView.addObject("department", departmentService.getById(departmentId));
        return modelAndView;
    }

    @PostMapping("/moder/departments/edit")
    public String editDepartmentModer(@RequestParam(value = "departmentId", required = true) long departmentId,
                                    @RequestParam(value = "nameDepartment", required = false) String nameDepartment) {

        Department department = departmentService.getById(departmentId);
        department.setNameDepartment(nameDepartment);
        departmentService.update(department);
        return "redirect:/moder/departments/departments";
    }

    @GetMapping("/moder/departments/delete")
    public String deleteDepartmentsById(@RequestParam long departmentId) {
        if (departmentService.exists(departmentId)) {
            Department department = departmentService.getById(departmentId);
            departmentService.delete(department);
        }
        return "redirect:/moder/departments/departments";
    }

    @GetMapping("/moder/departments/departments")
    public ModelAndView getDepartments(@RequestParam(required = false, defaultValue = "42") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/departments/departments");
        modelAndView.addObject("departments", departmentService.getAll());
        return modelAndView;
    }
}
