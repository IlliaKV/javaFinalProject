package com.webdev.employeesofthecompany.repository;

import com.webdev.employeesofthecompany.domain.EventSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventSheetRepository extends JpaRepository<EventSheet, Long> {
}
