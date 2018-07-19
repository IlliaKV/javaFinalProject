package com.webdev.employeesofthecompany.repository;

import com.webdev.employeesofthecompany.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}
