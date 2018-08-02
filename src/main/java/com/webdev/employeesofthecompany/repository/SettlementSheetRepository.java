package com.webdev.employeesofthecompany.repository;

import com.webdev.employeesofthecompany.domain.SettlementSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementSheetRepository extends JpaRepository<SettlementSheet, Long> {

    @Query(nativeQuery = true, value = "select * from settlement_sheet ss " +
            "where ss.date between :dateFrom and :dateTo and ss.employee_id=:employeeId")
    List<SettlementSheet> findSalaryDataByDateAndEmployeeId(@Param("dateFrom") String dateFrom,
                                                         @Param("dateTo") String dateTo,
                                                         @Param("employeeId") long employeeId);
}
