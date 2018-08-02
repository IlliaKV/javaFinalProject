package com.webdev.employeesofthecompany.service.jpa;

import com.webdev.employeesofthecompany.domain.Employee;
import com.webdev.employeesofthecompany.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.webdev.employeesofthecompany.service.ParseDateService.parseDateToStringYyyyMmDd;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        employeeRepository.save(employee);
        return employee;
    }

    public Employee getById(long id){
        return employeeRepository.findById(id).get();
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public void delete(Employee employee){
        employeeRepository.delete(employee);
    }

    public boolean exists(long employeeId) {
        return employeeRepository.existsById(employeeId);
    }

    public void update(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee getByEmail(String email) {
        return employeeRepository.getEmployeeByEmail(email);
    }


    public String getSalary(Date dateFrom, Date dateTo, String emailOfEmployee){
        return employeeRepository.salaryCountByDateStartAndEmail(parseDateToStringYyyyMmDd(dateFrom), parseDateToStringYyyyMmDd(dateTo), emailOfEmployee);
    }

    public int getMonthlyHoursWorked(Date dateFrom, Date dateTo, String emailOfEmployee){
        return employeeRepository.monthlyHoursWorked(parseDateToStringYyyyMmDd(dateFrom), parseDateToStringYyyyMmDd(dateTo), emailOfEmployee);
    }
}
