import { useRouter } from "next/navigation";
import { MouseEvent } from "react";

interface CardInfo {
  // image_src: string,
  header: string,
  description: string,
  // links?: LinkInfo[]
}

export default function FoodCard({ header, description }: CardInfo) {

  const router = useRouter();

  function handleDish(): void {
    router.push("/dish")
  }

  return (
    <div onClick={_ => handleDish()} className="p-3 bg-slate-200 max-w-96 rounded-lg overflow-hidden shadow-round-sm flex flex-col gap-8 hover:cursor-pointer">
      {/* <div className="w-full h-48 overflow-hidden">
          <img className="rounded-lg w-full h-full" src={image_src} alt={"project image"} />
        </div> */}
      <p className="font-bold text-4xl">{header}</p>
      <div className="h-16">
        <p>
          {description}
        </p>
      </div>
    </div>
  )
}