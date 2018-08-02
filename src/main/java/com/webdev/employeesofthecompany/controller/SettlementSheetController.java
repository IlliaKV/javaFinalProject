package com.webdev.employeesofthecompany.controller;

import com.webdev.employeesofthecompany.domain.Employee;
import com.webdev.employeesofthecompany.service.jpa.EmployeeService;
import com.webdev.employeesofthecompany.service.jpa.SettlementSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SettlementSheetController extends BaseSecurityController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SettlementSheetService settlementSheetService;

    @GetMapping("/moder/employees/settlementsheets")
    public ModelAndView getSettlementsheetsOfEmployeeByData(@RequestParam(value = "employeeId", required = true) long employeeId,
                                                      @RequestParam(value = "dateFrom", required = false) String dateFrom,
                                                      @RequestParam(value = "dateTo", required = false) String dateTo) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/employees/settlementsheets");
        modelAndView.addObject("employee", employeeService.getById(employeeId));
        modelAndView.addObject("settlementSheets", settlementSheetService.findSalaryDataByDateAndEmployeeId(dateFrom, dateTo, employeeId));

        return modelAndView;
    }

    @GetMapping("/employees/settlementsheets")
    public ModelAndView getSettlementsheetsOfEmployee(@RequestParam(required = false, defaultValue = "42") String value) {

        ModelAndView modelAndView = modelAndViewSecurityBase("employees/settlementsheets");
        modelAndView.addObject("employee", getCurrentUser());
        modelAndView.addObject("settlementSheets", getCurrentUser().getSettlementSheets());

        return modelAndView;
    }

    private Employee getCurrentUser() {
        String userEmail = securityProcessor.getCurrentUserEmail();
        return employeeService.getByEmail(userEmail);
    }
}
