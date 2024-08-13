import { Component, signal } from '@angular/core';
import { FilterType, TodoModel } from '../../models/todo';

@Component({
  selector: 'app-todo',
  standalone: true,
  imports: [],
  templateUrl: './todo.component.html',
  styleUrl: './todo.component.css'
})
export class TodoComponent {
  todolist = signal<TodoModel[]>([
    { id: 1, title: 'Todo 1', completed: false , edditing: false},
    { id: 2, title: 'Todo 2', completed: true }

  ])

  filter = signal<FilterType>('all');
  changueFilter (filterString: FilterType){

  }
}
