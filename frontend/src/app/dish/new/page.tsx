"use client"

import Header from "@/components/Header/Header";
import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { checkStatusCode } from "@/errors/statusCode";
import { Dish } from "@/types/dish";
import { Ingredient } from "@/types/ingredient";
import { useRouter } from "next/navigation";
import { useState, useEffect, MouseEvent, FormEvent } from "react";

export default function NewDish() {

  const [ingredients, setIngredients] = useState<Ingredient[]>([])
  const [newDish, setNewDish] = useState<Dish>(new Dish())

  const router = useRouter()

  useEffect(() => {
    fetch(`http://localhost:8090/ingredient`, {
      method: 'GET',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => checkStatusCode(res, "Invalid call"))
      .then(data => {
        setIngredients(data)
      })
      .catch(err => console.log(err.message))
  }, [])


  function handleCheckBox(isChecked: boolean, ingredient: Ingredient): void {
    setNewDish((prevDish) => ({
      ...prevDish,
      ingredients: isChecked ? [...prevDish.ingredients, ingredient] : prevDish.ingredients.filter(item => item.id !== ingredient.id)
    }))
  }

  function handleSubmit(e: FormEvent<HTMLFormElement>): void {
    e.preventDefault();
    console.log(newDish)

    fetch(`http://localhost:8090/dish`, {
      method: 'POST',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt'),
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        name: newDish.name,
        description: newDish.description,
        price: newDish.price,
        ingredients: newDish.ingredients
      })
    })
      .then(res => checkStatusCode(res,"Invalid data"))
      .then(data => {
        console.log(data)
        router.push("/orders/new")
      })
      .catch(err => console.log(err.message))
  }

  return (
    <div>
      <Header></Header>
      <form className="flex flex-col gap-4" onSubmit={e => handleSubmit(e)}>
        <div className="flex flex-col gap-4">
          <div className="flex flex-row place-items-center gap-5 max-w-80">
            <Label>Name:</Label>
            <Input required value={newDish.name} onChange={e => setNewDish({ ...newDish, name: e.target.value })} />
          </div>
          <div className="flex flex-row place-items-center gap-5 max-w-80">
            <Label>Description:</Label>
            <Input required value={newDish.description} onChange={e => setNewDish({ ...newDish, description: e.target.value })} />
          </div>
          <div className="flex flex-row place-items-center gap-5 max-w-80">
            <Label>Price:</Label>
            <Input required type="number" onChange={e => setNewDish({...newDish, price: Number(e.target.value)})}/>
          </div>
          <div className="grid grid-cols-4 gap-2 max-w-xl">
            {ingredients?.map((value: Ingredient, index: number) => (
              <div key={index} className="flex flex-row place-items-center gap-1">
                <Checkbox id={value.id.toString()} onCheckedChange={e => handleCheckBox(e as boolean, value)} />
                <Label htmlFor={value.id.toString()}>{value.name}</Label>
              </div>
            ))}
          </div>
        </div>

        <div>
          <Button>Submit</Button>
        </div>
      </form>
    </div>
  )
}