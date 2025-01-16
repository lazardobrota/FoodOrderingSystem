import { useRouter } from "next/navigation";
import { MouseEvent, MouseEventHandler } from "react";

interface CardInfo {
  // image_src: string,
  
  header: string,
  description: string,
  price: number,
  onClick: MouseEventHandler<HTMLDivElement> | undefined,
  // links?: LinkInfo[]
}

export default function FoodCard({ header, description, price, onClick }: CardInfo) {

  const router = useRouter();

  function handleDish(): void {
    router.push("/dish")
  }

  return (
    <div onClick={onClick} className="p-3 border-2 shadow-md  w-5/6 h-44 rounded-lg overflow-hidden shadow-round-sm flex flex-col gap-4 hover:cursor-pointer">
      {/* <div className="w-full h-48 overflow-hidden">
          <img className="rounded-lg w-full h-full" src={image_src} alt={"project image"} />
        </div> */}
      <div>
        <p className="font-bold text-2xl">{header}</p>
      </div>
      <div className="h-16">
        <p>{description}</p>
      </div>
      <div>
        <p className="font-bold">$ {price}.00</p>
      </div>
    </div>
  )
}