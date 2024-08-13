package com.example.todoapp.service;

import com.example.todoapp.api.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Optional<Todo> getTodoById(Integer id) {
        return todoRepository.findById(id);
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public void addTodo(Todo todo) {
        todoRepository.save(todo);
    }

    public void updateTodo(Integer id, Todo updatedTodo) {
        todoRepository.update(id, updatedTodo);
    }

    public void deleteTodoById(Integer id) {
        todoRepository.deleteById(id);
    }
}