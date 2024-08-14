import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TodoModel } from '../models/todo';

@Injectable({
  providedIn: 'root'
})

export class TodoService {
  private apiUrl = 'http://localhost:8080'; // URL base de tu API

  constructor(private http: HttpClient) { }

  // Obtener todos los todos
  getTodos(): Observable<TodoModel[]> {
    return this.http.get<TodoModel[]>(`${this.apiUrl}/todos`);
  }

  // Obtener un todo por ID
  getTodoById(id: number): Observable<TodoModel> {
    return this.http.get<TodoModel>(`${this.apiUrl}/todo?id=${id}`);
  }

  // Agregar un nuevo todo
  addTodo(todo: TodoModel): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/todo`, todo);
  }

  // Actualizar un todo existente
  updateTodo(id: number, todo: TodoModel): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/todo?id=${id}`, todo);
  }

  // Eliminar un todo por ID
  deleteTodoById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/todo?id=${id}`);
  }
}