package com.example.todoapp.api.model;

import java.time.LocalDateTime;

public class Todo {
    private Integer id;
    private String title;
    private LocalDateTime creationDate;
    private state state;

    public Todo(Integer id, String title, LocalDateTime creationDate, state state) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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