import Header from "@/components/Header/Header";

export default function UserNew() {
  return (
    <div className="flex flex-col gap-14">
      <Header></Header>
      <div className="flex flex-col items-center">  
        <form className="flex flex-col gap-5 px-80 size-7/12 items-center">
          <div className="size-full flex flex-col gap-5">
            <div className="flex flex-col gap-2">
              <label>Name </label>
              <input className="bg-slate-400 rounded-sm" required name="name" />
            </div>
            <div className="flex flex-col gap-2">
              <label>Lastname </label>
              <input className="bg-slate-400 rounded-sm" required name="lastname" />
            </div>
            <div className="flex flex-col gap-2">
              <label>Email </label>
              <input className="bg-slate-400 rounded-sm" required name="email" />
            </div>
            <div className="flex flex-col gap-2">
              <label>Password </label>
              <input className="bg-slate-400 rounded-sm" required name="password" />
            </div>
          </div>

          <div className="size-full flex flex-col gap-2">
            <div className="flex flex-row gap-2">
              <div>
                <input type="checkbox" />
              </div>
              <label>Can read users </label>
            </div>
            <div className="flex flex-row gap-2">
              <div>
                <input type="checkbox" />
              </div>
              <label>Can create users </label>
            </div>
            <div className="flex flex-row gap-2">
              <div>
                <input type="checkbox" />
              </div>
              <label>Can update users </label>
            </div>
            <div className="flex flex-row gap-2">
              <div>
                <input type="checkbox" />
              </div>
              <label>Can delete users </label>
            </div>
          </div>

          <button className="bg-green-400 px-4 py-2 rounded-full">Submit</button>
        </form>
      </div>
    </div>
  )
}