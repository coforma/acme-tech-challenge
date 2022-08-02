package com.acme.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public List<Event> list() {
        return repository.findAll();
    }
    public void deleteEventById(Long id) {
        Event event = repository.findById(id).orElse(null);
        repository.delete(event);
    }
}
