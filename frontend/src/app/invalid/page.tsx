import Header from "@/components/Header/Header";

export default function Invalid() {
  return (
    <div className="flex flex-col gap-48">
      <Header></Header>
      <h1 className="text-5xl text-center text-red-600">Your credentials are invalid</h1>
    </div>
  )
}