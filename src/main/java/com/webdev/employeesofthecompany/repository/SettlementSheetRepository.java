package com.webdev.employeesofthecompany.repository;

import com.webdev.employeesofthecompany.domain.SettlementSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementSheetRepository extends JpaRepository<SettlementSheet, Long> {
}
