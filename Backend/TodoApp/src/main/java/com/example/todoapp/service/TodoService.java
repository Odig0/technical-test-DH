package com.example.todoapp.service;

import com.example.todoapp.api.model.Todo;
import com.example.todoapp.api.model.Todo.state;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private List<Todo> todoList;

    public TodoService() {
        todoList = new ArrayList<>();

        Todo todo1 = new Todo(1, "Tarea 1", LocalDateTime.now(), state.IN_PROGRESS);
        Todo todo2 = new Todo(2, "Tarea 2", LocalDateTime.now().minusDays(1), state.DONE);
        Todo todo3 = new Todo(3, "Tarea 3", LocalDateTime.now().minusDays(2), state.IN_PROGRESS);

        todoList.addAll(Arrays.asList(todo1, todo2, todo3));
    }

    public Optional<Todo> getTodoById(Integer id) {
        return todoList.stream().filter(todo -> todo.getId().equals(id)).findFirst();
    }

    public List<Todo> getAllTodos() {
        return todoList;
    }

    public void addTodo(Todo todo) {
        todoList.add(todo);
    }

    public void updateTodo(Integer id, Todo updatedTodo) {
        getTodoById(id).ifPresent(todo -> {
            todo.setName(updatedTodo.getName());
            todo.setState(updatedTodo.getState());
        });
    }

    public void deleteTodoById(Integer id) {
        todoList.removeIf(todo -> todo.getId().equals(id));
    }
}