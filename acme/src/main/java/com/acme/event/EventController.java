package com.acme.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public Collection<Event> findEvents() {
        return eventService.list();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable(value = "id") Long eventId) {
        return eventRepository.findById(eventId).get();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event updateEvent(
            @PathVariable("id") final String id, @RequestBody final Event record) {
        return record;
    }

    @PostMapping("/events")
    public Event createEvent(@Valid @RequestBody Event record) {
        return eventRepository.save(record);
    }
}