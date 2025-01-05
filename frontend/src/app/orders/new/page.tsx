"use client"

import FoodCard from "@/components/food_card/FoodCard";
import Header from "@/components/Header/Header";

export default function NewOrder() {
    return (
        <div className="flex flex-col gap-4">
            <Header></Header>

            <div className="grid grid-cols-3 gap-4 justify-items-center">
                <FoodCard header="Burger" description="dwadwadawdawdawdawdawdawdaAdawdwadawad"></FoodCard>
                <FoodCard header="Burger" description="dwadwadawdawdawdawdawdawdaAdawdwadawad"></FoodCard>
                <FoodCard header="Burger" description="dwadwadawdawdawdawdawdawdaAdawdwadawad"></FoodCard>
                <FoodCard header="Burger" description="dwadwadawdawdawdawdawdawdaAdawdwadawad"></FoodCard>
                <FoodCard header="Burger" description="dwadwadawdawdawdawdawdawdaAdawdwadawad"></FoodCard>
                <FoodCard header="Burger" description="dwadwadawdawdawdawdawdawdaAdawdwadawad"></FoodCard>
            </div>
        </div>
    )
}