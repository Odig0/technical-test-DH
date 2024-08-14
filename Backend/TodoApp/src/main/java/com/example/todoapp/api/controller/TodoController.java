package com.example.todoapp.api.controller;

import com.example.todoapp.api.dto.TodoDTO;
import com.example.todoapp.api.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public List<TodoDTO> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(params = "id")
    public ResponseEntity<TodoDTO> getTodoById(@RequestParam Integer id) {
        return todoRepository.findById(id)
                .map(todo -> ResponseEntity.ok(convertToDTO(todo)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping
    public void addTodo(@Valid @RequestBody TodoDTO todoDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException("Invalid data for the task");
        }
        Todo todo = convertToEntity(todoDTO);
        todoRepository.addTodo(todo);
    }

    @PutMapping
    public void updateTodo(@RequestParam Integer id, @Valid @RequestBody TodoDTO todoDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException("Invalid data for the task");
        }

        Todo existingTodo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Todo not found"));

        // Update the fields of the existing Todo item with the values from the DTO
        existingTodo.setTitle(todoDTO.getTitle());
        existingTodo.setCreationDate(todoDTO.getCreationDate() != null ? todoDTO.getCreationDate() : LocalDateTime.now());
        existingTodo.setState(Todo.State.valueOf(todoDTO.getState() != null ? todoDTO.getState() : "IN_PROGRESS"));
        existingTodo.setCompleted(todoDTO.isCompleted()); // Ensure the completed status is updated

        // Save the updated Todo item
        todoRepository.update(id, existingTodo);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> deleteTodoById(@RequestParam Integer id) {
        if (todoRepository.findById(id).isPresent()) {
            todoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 Not Found if the todo doesn't exist
        }
    }

    // Conversion methods between DTO and entity
    private TodoDTO convertToDTO(Todo todo) {
        TodoDTO dto = new TodoDTO();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        dto.setCreationDate(todo.getCreationDate());
        dto.setState(todo.getState().name());
        dto.setCompleted(todo.isCompleted());
        return dto;
    }

    private Todo convertToEntity(TodoDTO dto) {
        Todo todo = new Todo(dto.getTitle(),
                dto.getCreationDate() != null ? dto.getCreationDate() : LocalDateTime.now(),
                Todo.State.valueOf(dto.getState() != null ? dto.getState() : "IN_PROGRESS"),
                dto.isCompleted());
        todo.setId(dto.getId());
        return todo;
    }
}
