package com.example.todoapp.api.model;

import java.time.LocalDateTime;

public class Todo {
    private Integer id;
    private String name;
    private LocalDateTime creationDate;
    private state state;

    public Todo(Integer id, String name, LocalDateTime creationDate, state state) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public state getState() {
        return state;
    }

    public void setState(state state) {
        this.state = state;
    }

    public enum state {
        DONE,
        IN_PROGRESS
    }
}