package com.webdev.employeesofthecompany.service.SalaryScheeduleService;

import com.webdev.employeesofthecompany.domain.Employee;
import com.webdev.employeesofthecompany.service.jpa.EmployeeService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AutomaticallyCalculateSalaryService {

    @Autowired
    private EmployeeService employeeService;

    public void run(){
        Date date = new Date();
        List<Employee> employees = employeeService.getAll();
        for (Employee e: employees) {
            System.out.println(e.getEmail() + "\t\t\t" + employeeService.getSalary(date, new DateTime(date).minusMonths(3).toDate(), e.getEmail()));
        }
    }
}
