package com.example.task.service;

import java.util.UUID;

public class Task {
    public enum Status {
        EN_COURS,
        TERMINE
    }

    private final String id;
    private final String description;
    private Status status;

    public Task(String description) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.status = Status.EN_COURS;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void markAsDone() {
        this.status = Status.TERMINE;
    }
}

