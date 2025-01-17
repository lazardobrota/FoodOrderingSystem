import { Dish } from "./dish";
import { User } from "./user";

export interface Order {
  id: number,
  status: OrderStatus,
  createdBy: User,
  active: boolean,
  createdDate: string,
  dishes: Dish[]
}

export interface DateParams {
  givenDate: string
}

export class SearchParams {
  userEmail: string = ""
  startDate: string = ""
  endDate: string = ""
  status: number = 0
}

export class CartOrder {
  createdDate: Date = new Date()
  dishes: Dish[] = [] 
}

export enum OrderStatus {
  ORDERED     = "ordered",
  PREPARING   = "preparing",
  IN_DELIVERY = "in_delivery",
  DELIVERED   = "delivered",
  CANCEL      = "cancel",
}

export enum OrderStatusInt {
  ORDERED     = 1 << 0,
  PREPARING   = 1 << 1,
  IN_DELIVERY = 1 << 2,
  DELIVERED   = 1 << 3,
  CANCEL      = 1 << 4,
}