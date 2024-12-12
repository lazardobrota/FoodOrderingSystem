"use client"

import Header from "@/components/Header/Header";
import { checkStatusCode } from "@/errors/statusCode";
import { UserLogin, UserPermissions } from "@/types/user";
import { useRouter } from "next/navigation";
import { FormEvent, useState } from "react";

export default function Login() {

  const [userLogin, setUserLogin] = useState<UserLogin>(new UserLogin)

  const router = useRouter()

  function handleSubmit(e: FormEvent<HTMLFormElement>, userLogin: UserLogin): void {
    e.preventDefault()

    fetch('http://localhost:8090/user/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email: userLogin.email,
        password: userLogin.password
      })
    })
      .then(res => checkStatusCode(res))
      .then(data => {
        console.log(data)
        const map: Record<string, boolean> = data.user.permissions
        localStorage.setItem("jwt", data.token)
        localStorage.setItem(UserPermissions.CanCreateUsers, map[UserPermissions.CanCreateUsers].toString())
        localStorage.setItem(UserPermissions.CanDeleteUsers, map[UserPermissions.CanDeleteUsers].toString())
        localStorage.setItem(UserPermissions.CanReadUsers, map[UserPermissions.CanReadUsers].toString())
        localStorage.setItem(UserPermissions.CanUpdateUsers, map[UserPermissions.CanUpdateUsers].toString())

        router.push("/home")
      })
      .catch(err => {
        window.alert(err)
      })
  }

  return (
    <div className="flex flex-col gap-14">
      <Header></Header>
      <form className="flex flex-col gap-5 px-80 size-full items-center" onSubmit={e => handleSubmit(e, userLogin)}>
        <div className="flex flex-col gap-2">
          <label>Email </label>
          <input className="bg-slate-400 rounded-sm" required name="email" value={userLogin.email} onChange={e => setUserLogin({ ...userLogin, email: e.target.value })} />
        </div>
        <div className="flex flex-col gap-2">
          <label>Password </label>
          <input type="password" className="bg-slate-400 rounded-sm" required name="password" value={userLogin.password} onChange={e => setUserLogin({ ...userLogin, password: e.target.value })} />
        </div>

        <button className="bg-green-400 hover:bg-green-500 px-4 py-2 rounded-full">Submit</button>
      </form>
    </div>
  )
}