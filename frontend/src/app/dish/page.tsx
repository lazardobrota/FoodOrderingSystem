import { Counter } from "@/components/counter/Counter";
import Header from "@/components/Header/Header";
import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { Label } from "@/components/ui/label";

export default function Dish() {

  return (
    <div className="flex flex-col gap-4">
      <Header></Header>
      <div className="flex flex-col gap-6">
        <p className="font-bold text-4xl">Header</p>
        <div className="h-16">
          <p>
            Description
          </p>
        </div>

        <div className="flex flex-col gap-2">
          <div className="flex flex-row place-items-center gap-5 max-w-80">
            <Checkbox id="ingredient1" />
            <Label htmlFor="ingredient1">Ingredient1</Label>
          </div>
          <div className="flex flex-row place-items-center gap-5 max-w-80">
            <Checkbox id="ingredient2" />
            <Label htmlFor="ingredient2">Ingredient2</Label>
          </div>
        </div>

        <div className="flex flex-row gap-2 max-w-40">
          <Counter></Counter>
          <Button>Add to order</Button>
        </div>
      </div>
    </div>
  )
}