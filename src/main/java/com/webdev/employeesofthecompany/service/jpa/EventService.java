package com.webdev.employeesofthecompany.service.jpa;

import com.webdev.employeesofthecompany.domain.Event;
import com.webdev.employeesofthecompany.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event save(Event event){
        eventRepository.save(event);
        return event;
    }

    public Event getById(long id){
        return eventRepository.findById(id).get();
    }

    public List<Event> getAll(){
        return eventRepository.findAll();
    }

    public void delete(Event event){
        eventRepository.delete(event);
    }

    public boolean exists(long eventId) {
        return eventRepository.existsById(eventId);
    }

    public void update(Event event){
        eventRepository.save(event);
    }
}
