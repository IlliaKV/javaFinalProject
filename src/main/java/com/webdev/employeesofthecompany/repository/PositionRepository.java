package com.webdev.employeesofthecompany.repository;

import com.webdev.employeesofthecompany.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long>{
}
