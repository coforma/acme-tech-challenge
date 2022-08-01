package com.acme.common;

public class AcmeHello {

    private final long id;
    private final String content;

    public AcmeHello(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}