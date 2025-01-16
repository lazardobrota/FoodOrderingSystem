import { MouseEventHandler } from "react";
import { Button } from "../ui/button";
import { Label } from "../ui/label";
import { FaMinus } from "react-icons/fa";
import { FaPlus } from "react-icons/fa";

interface CounterParams {
  label: string
  onMinus: MouseEventHandler<HTMLDivElement> | undefined,
  onPlus: MouseEventHandler<HTMLDivElement> | undefined,
  onAddOrder: MouseEventHandler<HTMLDivElement> | undefined,
}

export function Counter({label, onMinus, onPlus, onAddOrder}: CounterParams) {
  return (
    <div className="flex flex-row gap-4 items-center">

      <div onClick={onMinus} className="rounded-full bg-slate-200 p-2 hover:bg-slate-300 transition-all">
        <FaMinus></FaMinus>
      </div>
      <Label className="w-2 text-center">{label}</Label>
      <div onClick={onPlus} className="rounded-full bg-slate-200 p-2 hover:bg-slate-300 transition-all">
        <FaPlus>+</FaPlus>
      </div>
    </div>
  )
}