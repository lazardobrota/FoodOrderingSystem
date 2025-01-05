import { Button } from "../ui/button";
import { Label } from "../ui/label";

export function Counter() {
  return (
    <div className="flex flex-row gap-4 items-center">
      <Button>-</Button>

      <Label>1</Label>

      <Button>+</Button>
    </div>
  )
}