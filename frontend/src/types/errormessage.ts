import { OrderStatus } from "./order";
import { User } from "./user";

export interface ErrorMessage {
    id: number
    user: User
    date: string
    status: OrderStatus
    message: string
}