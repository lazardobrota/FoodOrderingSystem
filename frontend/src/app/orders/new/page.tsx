"use client"

import FoodCard from "@/components/food_card/FoodCard";
import Header from "@/components/Header/Header";
import { FoodModal } from "@/components/my-ui/foodmodal";
import { Dish } from "@/types/dish";
import Link from "next/link";
import { useEffect, useState } from "react";

export default function NewOrder() {

  const [dishes, setDishes] = useState<Dish[]>([])
  const [dishCart, setDishCart] = useState<Dish[]>([]) //TODO use as cart
  const [dishSelected, setDishSelected] = useState<Dish | null>(null)
  const [modal, setModal] = useState<boolean>(false)

  useEffect(() => {
    fetch(`http://localhost:8090/dish`, {
      method: "GET",
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => {
        setDishes(data.content)
      })
  }, [])


  function handleCardClick(dishId: number): void {
    fetch(`http://localhost:8090/dish/${dishId}`, {
      method: "GET",
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => {
        setDishSelected(data)
        setModal(!modal)
      })
  }

  function handleAddToCart(): void {
    setModal(false)
  }


  return (
    <div className="flex flex-col gap-4">
      <Header></Header>

      <div className="grid grid-cols-3 gap-4 justify-items-center">
        {dishes.map((dish: Dish, index: number) => (
          <FoodCard onClick={_ => handleCardClick(dish.id)} key={index} description={dish.description} header={dish.name} price={dish.price}></FoodCard>
        ))
        }
      </div>

      { modal && dishSelected !== null && <FoodModal onClose={_ => setModal(false)} onAddToCart={_ => handleCardClick} dish={dishSelected} />}
    </div>
  )
}