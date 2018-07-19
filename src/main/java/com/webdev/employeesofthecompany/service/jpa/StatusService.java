package com.webdev.employeesofthecompany.service.jpa;

import com.webdev.employeesofthecompany.domain.Status;
import com.webdev.employeesofthecompany.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public Status save(Status status){
        statusRepository.save(status);
        return status;
    }

    public Status getById(long id){
        return statusRepository.findById(id).get();
    }

    public List<Status> getAll(){
        return statusRepository.findAll();
    }

    public void delete(Status status){
        statusRepository.delete(status);
    }

    public boolean exists(long statusId) {
        return statusRepository.existsById(statusId);
    }

    public void update(Status status){
        statusRepository.save(status);
    }
}
