package com.example.todoapp.repository;

import com.example.todoapp.api.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    Optional<Todo> findById(Integer id);
    List<Todo> findAll();
    void save(Todo todo);
    void update(Integer id, Todo todo);
    void deleteById(Integer id);
}