import Header from "@/components/Header/Header";
import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

export default function NewDish() {
  return (
    <form className="flex flex-col gap-4">
      <Header></Header>

      <div className="flex flex-col gap-4">
        <div className="flex flex-row place-items-center gap-5 max-w-80">
          <Label>Name:</Label>
          <Input />
        </div>
        <div className="flex flex-row place-items-center gap-5 max-w-80">
          <Label>Description:</Label>
          <Input />
        </div>
        <div className="flex flex-row place-items-center gap-5 max-w-80">
          <Label>Add new ingredient:</Label>
          <Input />
        </div>
        <div className="flex flex-row place-items-center gap-5 max-w-80">
          <Checkbox id="ingredient1" />
          <Label htmlFor="ingredient1">Ingredient1</Label>
        </div>
        <div className="flex flex-row place-items-center gap-5 max-w-80">
          <Checkbox id="ingredient2" />
          <Label htmlFor="ingredient2">Ingredient2</Label>
        </div>
      </div>

      <div>
        <Button>Submit</Button>
      </div>
    </form>
  )
}