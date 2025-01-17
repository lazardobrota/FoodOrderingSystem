import { Dish } from "@/types/dish";
import { FormEventHandler, MouseEventHandler, useState } from "react";
import { Checkbox } from "../ui/checkbox";
import { Label } from "../ui/label";
import { Counter } from "../counter/Counter";
import { Button } from "../ui/button";
import { Ingredient } from "@/types/ingredient";
import { IoMdClose } from "react-icons/io";

interface FoodModalParams {
  dish: Dish
  onClose(): void,
  onMinus(dish: Dish): void,
  onPlus(dish: Dish): void,
}

export function FoodModal({ onClose, onMinus, onPlus, dish }: FoodModalParams) {

  const [ingredients, setIngredients] = useState<Ingredient[]>([])
  const [counter, setCounter] = useState<number>(0)

  function handleCheckBox(isChecked: boolean, ingredient: Ingredient): void {
    setIngredients(isChecked ? [...ingredients, ingredient] : ingredients.filter(item => item.id !== ingredient.id))
  }

  function handleOnMinus(): void {
    if (counter > 0) {
      setCounter(counter - 1)
      onMinus(dish)
    }
  }

  function handleOnPlus(): void {
    setCounter(counter + 1)
    onPlus(dish)
  }

  return (
    <div onClick={_ => onClose()} className="fixed size-full flex items-center justify-center bg-black bg-opacity-20">
      <div onClick={e => e.stopPropagation()} className="bg-white rounded-lg shadow-xl">
        <div onClick={onClose} className="flex flex-row-reverse p-2">
          <IoMdClose className="size-8 rounded-full p-1 bg-slate-200 hover:bg-slate-300 transition-all"></IoMdClose>
        </div>
        <div className=" px-10 pb-10">
          <div className="flex flex-col gap-6">
            <div>
              <p className="font-bold text-4xl">{dish.name}</p>
            </div>
            <div className="max-w-96">
              <p>{dish.description}</p>
            </div>
            <div>
              <p className="text-green-800">${dish.price}.00</p>
            </div>
            <div className="flex flex-col gap-5">
              {dish.ingredients.map((value: Ingredient, index: number) => (
                <div key={index} className="flex flex-row place-items-center gap-1">
                  <Checkbox id={value.id.toString()} onCheckedChange={e => handleCheckBox(e as boolean, value)} />
                  <Label htmlFor={value.id.toString()}>{value.name}</Label>
                </div>
              ))}
            </div>

            <div className="flex flex-row gap-4 justify-between">
              <Counter label={counter.toString()} onMinus={_ => handleOnMinus()} onPlus={_ => handleOnPlus()}></Counter>
              <Button onClick={_ => onClose()} className="basis-2/3">Add to order     $ {counter * dish.price}.00</Button>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
} 