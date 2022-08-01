package com.acme.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventRepository repository;

    @GetMapping("/")
    public Collection<Event> findEvents() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event updateEvent(
            @PathVariable("id") final String id, @RequestBody final Event record) {
        return record;
    }
}