// src/app/components/todo/todo.component.ts

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TodoFacade } from '../../services/todo-facade.service';
import { TodoModel, FilterType } from '../../models/todo';
import { AsyncPipe, NgForOf } from '@angular/common';
import { signal } from '@angular/core';


@Component({
  selector: 'app-todo',
  standalone: true,
  imports: [CommonModule, FormsModule, AsyncPipe, NgForOf],
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent  {
  newTodoTitle = '';
  filter: FilterType = 'all'; // Usa una variable normal en lugar de `signal`
  editedTodo!: TodoModel ; // Agrega una variable para el título editado
  editedTodoTitle: string = "";

  todos$ = this.todoFacade.todos$;


  constructor(private todoFacade: TodoFacade) {}


  

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
    const updatedTodo = { ...todo, title: this.editedTodoTitle.trim() };
    this.todoFacade.updateTodo(todo.id!, updatedTodo);
  }

  deleteTodo(todo: TodoModel) {
    this.todoFacade.deleteTodoById(todo.id!);
  }

  changeFilter(filter: FilterType) {
    // console.log('Changing filter in component:', filter); 
    this.todoFacade.getFilteredTodos(filter); 
  }
  preparedForEdit(todo: TodoModel) {
    this.editedTodoTitle = todo.title;
    this.editedTodo = todo;
  }
}
