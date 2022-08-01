package com.acme.config;

import com.acme.event.Event;
import com.acme.event.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDatabase {
    private static final Logger log = LoggerFactory.getLogger(InitDatabase.class);

    @Bean
    CommandLineRunner seedDatabase(EventRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Event(201, "Disaster 202")));
            log.info("Preloading " + repository.save(new Event(202, "Disaster 203")));
        };
    }
}
