package com.webdev.employeesofthecompany.service.jpa;

import com.webdev.employeesofthecompany.domain.Role;
import com.webdev.employeesofthecompany.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByName(String nameRole) {
        return roleRepository.getRoleByName(nameRole);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role save(Role role){
        roleRepository.save(role);
        return role;
    }
}
