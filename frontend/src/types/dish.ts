import { Ingredient } from "./ingredient";

export class Dish {
  id: number = 0
  name: string = ""
  description: string =""
  price: number = 0
  ingredients: Ingredient[] = []
}
