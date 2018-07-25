package com.webdev.employeesofthecompany.service.SalaryScheeduleService;

import com.webdev.employeesofthecompany.domain.Employee;
import com.webdev.employeesofthecompany.domain.SettlementSheet;
import com.webdev.employeesofthecompany.service.jpa.EmployeeService;
import com.webdev.employeesofthecompany.service.jpa.SettlementSheetService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.webdev.employeesofthecompany.service.ParseDateService.parseDateToStringYyyyMmDd;

@Service
public class AutomaticallyCalculateSalaryService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SettlementSheetService settlementSheetService;

    @Scheduled(cron = "0 0 15 1 * ?")
    public void run(){
        Date date = new Date();
        List<Employee> employees = employeeService.getAll();
        for (Employee employee: employees) {
            SettlementSheet settlementSheet = new SettlementSheet();
            settlementSheet.setDate(date);
            settlementSheet.setEmployee(employee);
            settlementSheet.setSalary(new BigDecimal(
                    employeeService.getSalary(parseDateToStringYyyyMmDd(new DateTime(date).minusMonths(1).toDate()),
                                            parseDateToStringYyyyMmDd(date),
                                            employee.getEmail()
                    )
            ));
            settlementSheetService.save(settlementSheet);
            //System.out.println(employee.getEmail() + "\t\t\t" + employeeService.getSalary(parseDateToStringYyyyMmDd(new DateTime(date).minusMonths(1).toDate()), parseDateToStringYyyyMmDd(date), employee.getEmail()));
        }
    }
}
