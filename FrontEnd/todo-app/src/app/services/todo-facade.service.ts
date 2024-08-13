// src/app/services/todo-facade.service.ts

import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators'; 
import { TodoService } from './todo.service'; 
import { TodoModel, FilterType } from '../models/todo';

@Injectable({
  providedIn: 'root'
})
export class TodoFacade {
  private todosSubject = new BehaviorSubject<TodoModel[]>([]);
  todos$ = this.todosSubject.asObservable();

  private filterSubject = new BehaviorSubject<FilterType>('all');
  filter$ = this.filterSubject.asObservable();

  constructor(private todoService: TodoService) {
    this.loadTodos();
  }

  private loadTodos(): void {
    this.todoService.getTodos().subscribe(todos => {
      this.todosSubject.next(todos);
    });
  }

  addTodo(todo: TodoModel): void {
    this.todoService.addTodo(todo).subscribe(() => {
      this.loadTodos();
    });
  }

  updateTodo(id: number, todo: TodoModel): void {
    this.todoService.updateTodo(id, todo).subscribe(() => {
      this.loadTodos();
    });
  }

  deleteTodoById(id: number): void {
    this.todoService.deleteTodoById(id).subscribe(() => {
      this.loadTodos();
    });
  }

  changeFilter(filter: FilterType): void {
    this.filterSubject.next(filter);
  }

  getFilteredTodos(): Observable<TodoModel[]> {
    return this.todos$.pipe(
      map(todos => {
        const filter = this.filterSubject.value;
        console.log('Current filter:', filter);
        console.log('Todos before filtering:', todos);
        if (filter === 'all') return todos;
        if (filter === 'active') return todos.filter(todo => !todo.completed);
        if (filter === 'completed') return todos.filter(todo => todo.completed);
        return todos;
      })
    );
  }  
}
