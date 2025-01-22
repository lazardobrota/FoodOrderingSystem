import { CartOrder } from "@/types/order";
import { Counter } from "../counter/Counter";
import { useState } from "react";
import { Button } from "../ui/button";
import { IoMdClose } from "react-icons/io";
import { Dish } from "@/types/dish";
import { FaRegTrashCan } from "react-icons/fa6";
import { Label } from "../ui/label";
import { Input } from "../ui/input";
import { format } from "date-fns";
import { DateTimePicker24h } from "./datepicker";

interface CartParams {
  cart: CartOrder,
  onClose(): void,
  onRemove(index: number): void
  setDate(date: Date): void,
  makeOrder(): void
}

export function CartModal({ cart, onClose, onRemove, setDate, makeOrder }: CartParams) {

  function calculatePrice(): number {
    return cart.dishes.reduce((sum, dish) => sum + dish.price, 0)
  }

  return (
    <div onClick={_ => onClose()} className="fixed size-full flex flex-row-reverse bg-black bg-opacity-20 transition-all z-10">
      <div onClick={e => e.stopPropagation()} className="flex flex-col gap-4 bg-white">
        <div onClick={onClose} className="flex flex-row-reverse p-2">
          <IoMdClose className="size-8 rounded-full p-1 bg-slate-200 hover:bg-slate-300 transition-all cursor-pointer"></IoMdClose>
        </div>
        <div className="flex flex-col gap-4 justify-between p-10 h-full">
          <div className="flex flex-col gap-4">
            {cart.dishes.map((dish: Dish, index) => (
              <div key={index} className="flex flex-row gap-14 justify-between hover:bg-slate-100 p-3 transition-all rounded-lg">
                <div>
                  <p className="font-bold">{dish.name}</p>
                </div>
                <div onClick={() => onRemove(index)} className="p-2 bg-slate-200 hover:bg-slate-300 transition-all rounded-full">
                  <FaRegTrashCan className="size-4 "></FaRegTrashCan>
                </div>
              </div>
            ))}

            <div className="flex flex-row place-items-center gap-5 p-3">
              <Label>Date from:</Label>
              <DateTimePicker24h date={cart.createdDate} setDate={setDate}></DateTimePicker24h>
            </div>
          </div>

          <Button onClick={_ => makeOrder()}>Add to order     $ {calculatePrice()}.00</Button>
        </div>
      </div>
    </div>
  )
}