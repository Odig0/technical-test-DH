package com.example.todoapp.repository;

import com.example.todoapp.api.model.Todo;
import org.springframework.stereotype.Repository;
import java.util.concurrent.atomic.AtomicInteger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTodoRepository implements TodoRepository {
    private List<Todo> todoList;
    private AtomicInteger idCounter = new AtomicInteger(1);

    public InMemoryTodoRepository() {
        todoList = new ArrayList<>();
        // Inicialización de datos de ejemplo
        Todo todo1 = new Todo( "Comprar fruta ", LocalDateTime.now(), Todo.State.IN_PROGRESS);
        Todo todo2 = new Todo( "Recoger a mi hermano del colegio", LocalDateTime.now().minusDays(1), Todo.State.DONE);
        Todo todo3 = new Todo("Terminar prueba Tecnica", LocalDateTime.now().minusDays(2), Todo.State.IN_PROGRESS);

        todo1.setId(idCounter.getAndIncrement());
        todo2.setId(idCounter.getAndIncrement());
        todo3.setId(idCounter.getAndIncrement());

        todoList.addAll(Arrays.asList(todo1, todo2, todo3));

    }

    @Override
    public Optional<Todo> findById(Integer id) {
        return todoList.stream().filter(todo -> todo.getId().equals(id)).findFirst();
    }
    @Override
    public void addTodo(Todo todo) {
        todo.setId(idCounter.incrementAndGet());
        todoList.add(todo);
    }

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(todoList);
    }

    @Override
    public void save(Todo todo) {
        // Si el ID es null, se asigna un nuevo ID
        if (todo.getId() == null) {
            todo.setId(idCounter.incrementAndGet());
        }
        todoList.add(todo);
    }

    @Override
    public void update(Integer id, Todo updatedTodo) {
        findById(id).ifPresent(todo -> {
            todo.setTitle(updatedTodo.getTitle());
            todo.setCreationDate(updatedTodo.getCreationDate());
            todo.setState(updatedTodo.getState());
        });
    }

    @Override
    public void deleteById(Integer id) {
        todoList.removeIf(todo -> todo.getId().equals(id));
    }
}