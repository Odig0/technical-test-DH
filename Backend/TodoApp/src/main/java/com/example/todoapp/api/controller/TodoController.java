package com.example.todoapp.api.controller;

import com.example.todoapp.api.model.Todo;
import com.example.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo")
    public Todo getTodoById(@RequestParam Integer id) {
        Optional<Todo> todo = todoService.getTodoById(id);
        return todo.orElse(null);
    }

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping("/todo")
    public void addTodo(@RequestBody Todo todo) {
        todoService.addTodo(todo);
    }

    @PutMapping("/todo")
    public void updateTodo(@RequestParam Integer id, @RequestBody Todo updatedTodo) {
        todoService.updateTodo(id, updatedTodo);
    }

    @DeleteMapping("/todo")
    public void deleteTodoById(@RequestParam Integer id) {
        todoService.deleteTodoById(id);
    }
}