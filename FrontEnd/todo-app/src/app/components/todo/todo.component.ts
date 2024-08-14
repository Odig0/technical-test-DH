// src/app/components/todo/todo.component.ts

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TodoFacade } from '../../services/todo-facade.service';
import { TodoModel, FilterType } from '../../models/todo';
import { AsyncPipe, NgForOf } from '@angular/common';
import { signal } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-todo',
  standalone: true,
  imports: [CommonModule, FormsModule, AsyncPipe, NgForOf, HttpClientModule],
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {
  newTodoTitle = '';
  filter: FilterType = 'all'; // Usa una variable normal en lugar de `signal`

  todos$ = this.todoFacade.todos$;
  filteredTodos$: Observable<TodoModel[]>;

  constructor(private todoFacade: TodoFacade) {
    this.filteredTodos$ = this.todoFacade.getFilteredTodos();
  }

  ngOnInit() {
    this.filteredTodos$.subscribe(todos => {
      console.log('Tareas filtradas:', todos); // Verifica las tareas filtradas
    });
  }

  addTodo() {
    if (this.newTodoTitle.trim()) {
      const newTodo: TodoModel = {
        title: this.newTodoTitle,
        creationDate: new Date().toISOString(),
        completed: false
      };
      this.todoFacade.addTodo(newTodo);
      this.newTodoTitle = '';
    }
  }

  toggleComplete(todo: TodoModel) {
    const updatedTodo = { ...todo, completed: !todo.completed };
    this.todoFacade.updateTodo(todo.id!, updatedTodo);
  }

  editTodo(todo: TodoModel) {
    const updatedTodo = { ...todo, title: todo.title.trim() };
    this.todoFacade.updateTodo(todo.id!, updatedTodo);
  }

  deleteTodo(todo: TodoModel) {
    this.todoFacade.deleteTodoById(todo.id!);
  }

  changeFilter(filter: FilterType) {
    console.log('Changing filter in component:', filter); // Verifica el filtro
    this.todoFacade.changeFilter(filter); // Actualiza el filtro en el servicio
  }
}
