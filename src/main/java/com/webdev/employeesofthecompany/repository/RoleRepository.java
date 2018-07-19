package com.webdev.employeesofthecompany.repository;

import com.webdev.employeesofthecompany.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("from Role r where r.nameRole = :name")
    Role getRoleByName(@Param("name") String name);
}
