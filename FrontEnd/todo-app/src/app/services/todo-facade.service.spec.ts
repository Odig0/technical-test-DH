import { TestBed } from '@angular/core/testing';

import { TodoFacade } from '../services/todo-facade.service';

describe('TodoFacade', () => {
  let service: TodoFacade;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TodoFacade);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
