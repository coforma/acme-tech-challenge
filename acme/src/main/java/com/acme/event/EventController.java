package com.acme.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

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
    public ResponseEntity<Object> updateEvent(
            @PathVariable("id") final Long id, @RequestBody final Event record) {

        Optional<Event> eventOptional = eventRepository.findById(id);
        if (!eventOptional.isPresent())
            return ResponseEntity.notFound().build();

        record.setId(id);
        eventRepository.save(record);
        return ResponseEntity.noContent().build();
    }
    @CrossOrigin(origins = "http://localhost:8088")
    @PostMapping("/events")
    public Event createEvent(@Valid @RequestBody Event record) {
        return eventRepository.save(record);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id){
        eventService.deleteEventById(id);
        return new ResponseEntity("Event deleted: " + id, HttpStatus.OK);
    }
}