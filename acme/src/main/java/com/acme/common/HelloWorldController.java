package com.acme.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tests")
public class HelloWorldController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public AcmeHello greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new AcmeHello(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(value = "/records", method = RequestMethod.GET, produces = "application/json")
    public List<String> firstPage() {
        return new ArrayList<>();
    }
}