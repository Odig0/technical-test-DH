package com.example.todoapp.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class TodoDTO {

    private Integer id;

    @NotNull
            (message = "La tarea no puede estar vacia.")
    @Size(min = 3, max = 100, message = "La tarea debe tener  entre 3 y 100 caracteres.")
    private String title;

    private LocalDateTime creationDate;

    private String state;

    private boolean completed = false;

    // Constructor predeterminado
    public TodoDTO() {
        this.completed = false;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
