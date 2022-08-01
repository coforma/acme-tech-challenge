package com.acme.event;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Event {
    @Id
    private long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}