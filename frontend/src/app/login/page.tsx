"use client"

import Header from "@/components/Header/Header";
import { Toaster } from "@/components/ui/sonner";
import { checkStatusCode } from "@/errors/statusCode";
import { JwtClaims } from "@/types/auth";
import { permissionsStorage as permissionsStorage, UserLogin, UserPermissions, UserPermissionsInt } from "@/types/user";
import { jwtDecode, JwtPayload } from "jwt-decode";
import { useRouter } from "next/navigation";
import { FormEvent, useState } from "react";
import { toast } from "sonner";

export default function Login() {

  const [userLogin, setUserLogin] = useState<UserLogin>(new UserLogin)

  const router = useRouter()

  const permissionsMap: Record<string, number> = {
      [UserPermissions.CanCancelOrder]: UserPermissionsInt.CanCancelOrder,
      [UserPermissions.CanCreateUsers]: UserPermissionsInt.CanCreateUsers,
      [UserPermissions.CanDeleteUsers]: UserPermissionsInt.CanDeleteUsers,
      [UserPermissions.CanPlaceOrder]: UserPermissionsInt.CanPlaceOrder,
      [UserPermissions.CanReadUsers]: UserPermissionsInt.CanReadUsers,
      [UserPermissions.CanScheduleOrder]: UserPermissionsInt.CanScheduleOrder,
      [UserPermissions.CanSearchOrder]: UserPermissionsInt.CanSearchOrder,
      [UserPermissions.CanTrackOrder]: UserPermissionsInt.CanTrackOrder,
      [UserPermissions.CanUpdateUsers]: UserPermissionsInt.CanUpdateUsers,
    }

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
        const decoded =jwtDecode<JwtClaims>(data.token) 
        console.log(decoded)

        const map: Record<string, boolean> = data.user.permissions
        let permissions: number = 0
        localStorage.setItem("jwt", data.token)
        Object.entries(map).map(([key, value]) => (
          permissions = permissionsMap[key] === undefined ? permissions : permissions | permissionsMap[key]
        ))
        localStorage.setItem(permissionsStorage, permissions.toString())

        router.push("/home")
      })
      .catch(error => toast.error(error.message))
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
      <Toaster richColors/>
    </div>
  )
}