"use client"

import FoodCard from "@/components/food_card/FoodCard";
import Header from "@/components/Header/Header";
import { CartModal } from "@/components/my-ui/cartmodal";
import { FoodModal } from "@/components/my-ui/foodmodal";
import { Dish } from "@/types/dish";
import { CartOrder } from "@/types/order";
import Link from "next/link";
import { useEffect, useState } from "react";
import { TiShoppingCart } from "react-icons/ti";
import { format } from "date-fns";
import { checkStatusCode } from "@/errors/statusCode";
import { useRouter } from "next/navigation";
import { toast } from "sonner";
import { Toaster } from "@/components/ui/sonner";

export default function NewOrder() {

	const [dishes, setDishes] = useState<Dish[]>([])
	const [dishSelected, setDishSelected] = useState<Dish | null>(null)
	const [cartOrder, setCartOrder] = useState<CartOrder>(new CartOrder())
	const [dishModal, setDishModal] = useState<boolean>(false)
	const [cartModal, setCartModal] = useState<boolean>(false)

	const router = useRouter()

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
				setDishModal(!dishModal)
			})
	}

	function makeOrder(): void {
		fetch(`http://localhost:8090/order`, {
			method: 'POST',
			headers: {
				'Authorization': 'Bearer ' + window.localStorage.getItem('jwt'),
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				createdDate: format(cartOrder.createdDate, "yyyy-MM-dd HH:mm:ss"),
				dishes: cartOrder.dishes
			})
		}).then(res => checkStatusCode(res, "Invalid Data"))
			.then(data => {
				router.push("/orders")
			})
			.catch(err => {
				toast.error(err.message)
			})
	}

	function handleOnMinus(dish: Dish): void {
		setCartOrder((prevCart) => ({ ...prevCart, dishes: prevCart.dishes.slice(0, -1) }))
	}

	function handleOnPlus(dish: Dish): void {
		setCartOrder((prevCart) => ({ ...prevCart, dishes: [...prevCart.dishes, dish] }))
	}

	function onClose(): void {
		setDishModal(false)
		setCartOrder((prevCart) => ({ ...prevCart, dishes: prevCart.dishes.sort((a, b) => a.name.localeCompare(b.name)) }))
	}

	function removeFromList(index: number): void {
		if (index > -1) {
			setCartOrder((prevCart) => ({ ...prevCart, dishes: prevCart.dishes.filter((_, i) => i !== index) }));
		}
	}

	function setDate(date: Date): void {
		setCartOrder({ ...cartOrder, createdDate: date })
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
			<div className="fixed bg-blue-500 p-5 rounded-full right-16 bottom-16 cursor-pointer hover:scale-110 transition-all" onClick={_ => setCartModal(true)}>
				<TiShoppingCart className="size-14 text-white"></TiShoppingCart>
			</div>
			{dishModal && dishSelected !== null && <FoodModal onMinus={handleOnMinus} onPlus={handleOnPlus} onClose={onClose} dish={dishSelected} />}
			{cartModal && <CartModal makeOrder={makeOrder} setDate={setDate} cart={cartOrder} onClose={() => setCartModal(false)} onRemove={removeFromList}></CartModal>}

			<Toaster richColors />
		</div>
	)
}