"use client"

import Header from "@/components/Header/Header";
import { Toaster } from "@/components/ui/sonner";
import { checkStatusCode } from "@/errors/statusCode";
import { usePermissionCheck } from "@/hooks/credentials";
import { UpdateUser, UserPermissions, UserPermissionsInt } from "@/types/user";
import { AppRouterInstance } from "next/dist/shared/lib/app-router-context.shared-runtime";
import { useRouter } from "next/navigation";
import { FormEvent, useState } from "react";
import { toast } from "sonner";

export default function UserNew() {
  const [user, setUser] = useState<UpdateUser>(new UpdateUser())
  const router: AppRouterInstance = useRouter();

  usePermissionCheck(UserPermissionsInt.CanCreateUsers)

  function handleSubmit(e: FormEvent<HTMLFormElement>, user: UpdateUser): void {
    e.preventDefault();

    fetch('http://localhost:8090/user/create', {
      method: 'POST',
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt'),
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        name: user.name,
        lastname: user.lastname,
        email: user.email,
        password: user.password,
        permissions: user.permissions
      })
    })
    .then(res => checkStatusCode(res, "Email already taken"))
    .then(data => {
      console.log(data)
      router.push("/users")
    })
    .catch((error) => toast.error(error.message))
  }


  function hanleCheckBoxChange(key: string, value: boolean): void {
    setUser((prevUser) => ({
      ...prevUser,
      permissions: {
        ...prevUser.permissions,
        [key]: !value
      }
    }))
  }

  return (
    <div className="flex flex-col gap-14">
      <Header></Header>
      <div className="flex flex-col items-center">  
        <form className="flex flex-col gap-5 px-80 size-7/12 items-center" onSubmit={e => handleSubmit(e, user)}>
          <div className="size-full flex flex-col gap-5">
            <div className="flex flex-col gap-2">
              <label>Name </label>
              <input className="bg-slate-400 rounded-sm" value={user.name} onChange={e => setUser({...user, name: e.target.value})} required name="name" />
            </div>
            <div className="flex flex-col gap-2">
              <label>Lastname </label>
              <input className="bg-slate-400 rounded-sm" value={user.lastname} onChange={e => setUser({...user, lastname: e.target.value})} required name="lastname" />
            </div>
            <div className="flex flex-col gap-2">
              <label>Email </label>
              <input className="bg-slate-400 rounded-sm" value={user.email} onChange={e => setUser({...user, email: e.target.value})} required name="email" />
            </div>
            <div className="flex flex-col gap-2">
              <label>Password </label>
              <input type="password" className="bg-slate-400 rounded-sm" value={user.password} onChange={e => setUser({...user, password: e.target.value})} required name="password" />
            </div>
          </div>

          <div className="size-full flex flex-col gap-2">
            {Object.entries(user.permissions).map(([key, value]) => (
              <div key={key} className="flex flex-row gap-2">
                <div>
                  <input type="checkbox" checked={value} onChange={() => hanleCheckBoxChange(key, value)} />
                </div>
                <label>{key}</label>
              </div>
            ))}
          </div>

          <button className="bg-green-400 hover:bg-green-500 px-4 py-2 rounded-full">Submit</button>
        </form>
      </div>

      <Toaster richColors/>
    </div>
  )
}