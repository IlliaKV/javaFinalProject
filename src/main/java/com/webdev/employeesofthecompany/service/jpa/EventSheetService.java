package com.webdev.employeesofthecompany.service.jpa;

import com.webdev.employeesofthecompany.domain.EventSheet;
import com.webdev.employeesofthecompany.repository.EventSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventSheetService {

    @Autowired
    private EventSheetRepository eventSheetRepository;

    public EventSheet save(EventSheet eventSheet){
        eventSheetRepository.save(eventSheet);
        return eventSheet;
    }

    public EventSheet getById(long id){
        return eventSheetRepository.findById(id).get();
    }

    public List<EventSheet> getAll(){
        return eventSheetRepository.findAll();
    }

    public void delete(EventSheet eventSheet){
        eventSheetRepository.delete(eventSheet);
    }

    public boolean exists(long eventSheetId) {
        return eventSheetRepository.existsById(eventSheetId);
    }

    public void update(EventSheet eventSheet){
        eventSheetRepository.save(eventSheet);
    }
}
