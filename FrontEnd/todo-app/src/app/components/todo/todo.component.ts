// src/app/components/todo/todo.component.ts

import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'
import { TodoFacade } from '../../services/todo-facade.service';
import { TodoModel, FilterType } from '../../models/todo';
import { AsyncPipe, NgForOf } from '@angular/common'; // Importar AsyncPipe y NgForOf si estás usando Angular 14+
import { signal } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-todo',
  standalone: true,
  imports: [CommonModule, FormsModule, AsyncPipe, NgForOf, HttpClientModule], // Asegúrate de importar estos módulos
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent {
  newTodoTitle = '';
  filter = signal<FilterType>('all');

  todos$ = this.todoFacade.todos$;
  filteredTodos$ = this.todoFacade.getFilteredTodos();

  constructor(private todoFacade: TodoFacade) {}

  addTodo() {
    if (this.newTodoTitle.trim()) {
      const newTodo: TodoModel = {
        id: 5, 
        title: this.newTodoTitle,
        completed: false
      };
      this.todoFacade.addTodo(newTodo);
      this.newTodoTitle = '';
    }
  }

  toggleComplete(todo: TodoModel) {
    const updatedTodo = { ...todo, completed: !todo.completed };
    this.todoFacade.updateTodo(todo.id, updatedTodo);
  }

  editTodo(todo: TodoModel) {
    const updatedTodo = { ...todo, title: todo.title.trim() };
    this.todoFacade.updateTodo(todo.id, updatedTodo);
  }

  deleteTodo(todo: TodoModel) {
    this.todoFacade.deleteTodoById(todo.id);
  }

  changeFilter(filter: FilterType) {
    this.todoFacade.changeFilter(filter);
  }
}
