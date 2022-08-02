package com.acme;

import com.acme.event.Event;
import com.acme.event.EventRepository;
import com.acme.event.EventService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class AcmeApplicationTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EventRepository eventRepository;
    @Autowired
    private EventService eventService;

    @Test
    void contextLoads() {
    }

    @Test
    public void TestRecordsExist() {
        logger.info("Inserting Event {}", eventRepository.save(new Event(101, "Hurricane Calvin")));
        List<Event> events = eventService.list();
        Assert.assertEquals(events.size(), 4);
    }

}
