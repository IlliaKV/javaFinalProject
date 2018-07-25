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

    @Query(nativeQuery = true, value = "select sum(es.number_of_hours) * p.hourly_rate from event e, event_sheet es, employee em, position p where es.employee_id=em.id and es.event_id=e.id and em.position_id=p.id and es.date_start between :dateFrom and :dateTo and em.email=:emailOfEmployee")
    String salaryCountByDateStartAndEmail(@Param("dateFrom") String dateFrom,
                                          @Param("dateTo") String dateTo,
                                          @Param("emailOfEmployee") String emailOfEmployee);
}
