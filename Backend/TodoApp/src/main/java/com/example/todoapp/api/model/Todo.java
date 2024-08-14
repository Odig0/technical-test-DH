package com.example.todoapp.api.model;

import java.time.LocalDateTime;

public class Todo {
    private Integer id;
    private String title;
    private LocalDateTime creationDate;
    private State state;

    public Todo(String title, LocalDateTime creationDate, State state) {
        this.title = title;
        this.creationDate = creationDate;
        this.state = State.IN_PROGRESS;

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        DONE,
        IN_PROGRESS,
        UPDATED_AT
    }
}
