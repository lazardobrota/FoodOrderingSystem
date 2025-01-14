import { Ingredient } from "./ingredient";

export interface Dish {
  id: number,
  name: string,
  description: string,
  price: number,
  ingredients: Ingredient[]
}