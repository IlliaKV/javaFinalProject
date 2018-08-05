package com.webdev.employeesofthecompany.repository;

import com.webdev.employeesofthecompany.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select sum(event_sheet.number_of_hours) * position.hourly_rate \n" +
            "from event, event_sheet, employee, position \n" +
            "where event_sheet.employee_id=employee.id and event_sheet.event_id=event.id and employee.position_id=position.id and event_sheet.date_start between ? and ? and employee.email= ?", nativeQuery = true)
    BigDecimal salaryCountByDateStartAndEmail(Date dateFrom, Date dateTo, String emailOfEmployee);
}
