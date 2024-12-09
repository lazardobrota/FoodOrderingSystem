import Header from "@/components/Header/Header";

export default function Login() {
  return (
    <div className="flex flex-col gap-14">
      <Header></Header>
      <form className="flex flex-col gap-5 px-80 size-full items-center">
        <div className="flex flex-col gap-2">
            <label>Email </label>
            <input className="bg-slate-400 rounded-sm" required name="email"/>
        </div>
        <div className="flex flex-col gap-2">
            <label>Password </label>
            <input className="bg-slate-400 rounded-sm" required name="password"/>
        </div>

        <button className="bg-green-400 hover:bg-green-500 px-4 py-2 rounded-full">Submit</button>
      </form>
    </div>
  )
}