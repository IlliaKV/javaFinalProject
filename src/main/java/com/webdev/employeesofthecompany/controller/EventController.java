package com.webdev.employeesofthecompany.controller;

import com.webdev.employeesofthecompany.domain.Event;
import com.webdev.employeesofthecompany.service.jpa.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController extends BaseSecurityController{

    @Autowired
    private EventService eventService;

    @GetMapping("/moder/eventstype/create")
    public ModelAndView createEvent() {
        return modelAndViewSecurityBase("moder/eventstype/create");
    }

    @PostMapping("/moder/eventstype/create")
    public String createEvent(@ModelAttribute Event event) {
        eventService.save(event);
        return "redirect:/moder/eventstype/eventstype";
    }

    @GetMapping("/moder/eventstype/edit")
    public ModelAndView editEventModer(@RequestParam long eventId) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/eventstype/edit");
        modelAndView.addObject("event", eventService.getById(eventId));
        return modelAndView;
    }

    @PostMapping("/moder/eventstype/edit")
    public String editEventModer(@RequestParam(value = "eventId", required = true) long eventId,
                                      @RequestParam(value = "nameEvent", required = false) String nameEvent) {

        Event event = eventService.getById(eventId);
        event.setNameEvent(nameEvent);
        eventService.update(event);
        return "redirect:/moder/eventstype/eventstype";
    }

    @GetMapping("/moder/eventstype/delete")
    public String deleteEventById(@RequestParam long eventId) {
        if (eventService.exists(eventId)) {
            Event event = eventService.getById(eventId);
            eventService.delete(event);
        }
        return "redirect:/moder/eventstype/eventstype";
    }

    @GetMapping("/moder/eventstype/eventstype")
    public ModelAndView getEvents(@RequestParam(required = false, defaultValue = "42") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/eventstype/eventstype");
        modelAndView.addObject("events", eventService.getAll());
        return modelAndView;
    }
}
