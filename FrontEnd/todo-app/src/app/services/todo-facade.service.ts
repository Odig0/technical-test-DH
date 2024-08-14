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
  todoAux: TodoModel[] = [];

  private filterSubject = new BehaviorSubject<FilterType>('all');
  filter$ = this.filterSubject.asObservable();

  constructor(private todoService: TodoService) {
    this.loadTodos();
  }

  private loadTodos(): void {
    this.todoService.getTodos().subscribe(todos => {
      this.todoAux = todos;
      // console.log('Todos loaded:', this.todoAux);
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

  
  getFilteredTodos(filter: FilterType) {
    let todosFiltered: TodoModel[] = [];

    switch (filter) {
        case 'in_progress':
            todosFiltered = this.todoAux.filter(todo => !todo.completed);
            break;
        case 'done':
            todosFiltered = this.todoAux.filter(todo => todo.completed);
            break;
        case 'all':
        default:
            todosFiltered = this.todoAux;
            break;
    }

    this.todosSubject.next(todosFiltered);
  }
}
