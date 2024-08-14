export interface TodoModel {
    id?: number;
    title: string;
    creationDate: string;
    completed: boolean;
    editing?: boolean;
  }
  
  export type FilterType = 'all' | 'in_progress' | 'done';