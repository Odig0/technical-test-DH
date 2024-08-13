export interface TodoModel {
    id: number;
    title: string;
    completed: boolean;
    edditing?: boolean;
}
export type FilterType = 'all' | 'active' | 'completed';