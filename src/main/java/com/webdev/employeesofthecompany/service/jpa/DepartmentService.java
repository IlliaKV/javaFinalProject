package com.webdev.employeesofthecompany.service.jpa;

import com.webdev.employeesofthecompany.domain.Department;
import com.webdev.employeesofthecompany.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department save(Department department){
        departmentRepository.save(department);
        return department;
    }

    public Department getById(long id){
        return departmentRepository.findById(id).get();
    }

    public List<Department> getAll(){
        return departmentRepository.findAll();
    }

    public void delete(Department department){
        departmentRepository.delete(department);
    }

    public boolean exists(long departmentId) {
        return departmentRepository.existsById(departmentId);
    }

    public void update(Department department){
        departmentRepository.save(department);
    }
}
