package com.acme.event;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event")
public class EventController {
    
    @Autowired
    private EventRepository eventRepository;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    public Collection<Event> findEvents() {
        return eventRepository.findAll();
    }
    
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable(value = "id") Long eventId) {
        return eventRepository.findById(eventId).get();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateEvent(
            @PathVariable("id") final Long id, @RequestBody final Event record) {

        Optional<Event> eventOptional = eventRepository.findById(id);
        if (!eventOptional.isPresent())
            return ResponseEntity.notFound().build();

        record.setId(id);
        eventRepository.save(record);
        return ResponseEntity.noContent().build();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:8088")
    @PostMapping("/events")
    public Event createEvent(@Valid @RequestBody Event record) {
        return eventRepository.save(record);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id){
        
        Event event = eventRepository.findById(id).orElse(null);
        eventRepository.delete(event);

        return new ResponseEntity("Event deleted: " + id, HttpStatus.OK);
    }
    
}