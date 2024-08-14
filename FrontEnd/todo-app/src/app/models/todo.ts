export interface TodoModel {
    id?: number;
    title: string;
    completed: boolean;
    creationDate: string;
    editing?: boolean;
  }
  
  export type FilterType = 'all' | 'in_progress' | 'done';