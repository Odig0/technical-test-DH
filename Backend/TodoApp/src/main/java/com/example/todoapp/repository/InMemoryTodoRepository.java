package com.example.todoapp.repository;

import com.example.todoapp.api.model.Todo;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTodoRepository implements TodoRepository {
    private List<Todo> todoList;

    public InMemoryTodoRepository() {
        todoList = new ArrayList<>();
        // Inicialización de datos de ejemplo
        Todo todo1 = new Todo(1, "Tarea 1", LocalDateTime.now(), Todo.state.IN_PROGRESS);
        Todo todo2 = new Todo(2, "Tarea 2", LocalDateTime.now().minusDays(1), Todo.state.DONE);
        Todo todo3 = new Todo(3, "Tarea 3", LocalDateTime.now().minusDays(2), Todo.state.IN_PROGRESS);

        todoList.addAll(Arrays.asList(todo1, todo2, todo3));
    }

    @Override
    public Optional<Todo> findById(Integer id) {
        return todoList.stream().filter(todo -> todo.getId().equals(id)).findFirst();
    }

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(todoList);
    }

    @Override
    public void save(Todo todo) {
        todoList.add(todo);
    }

    @Override
    public void update(Integer id, Todo updatedTodo) {
        findById(id).ifPresent(todo -> {
            todo.setName(updatedTodo.getName());
            todo.setCreationDate(updatedTodo.getCreationDate());
            todo.setState(updatedTodo.getState());
        });
    }

    @Override
    public void deleteById(Integer id) {
        todoList.removeIf(todo -> todo.getId().equals(id));
    }
}