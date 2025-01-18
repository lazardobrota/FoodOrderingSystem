"use client"
import { permissionsStorage, UserPermissions, UserPermissionsInt } from "@/types/user";
import { useRouter } from "next/navigation"

export default function Header() {
  
  const router = useRouter();
  
  function handleLogout(): void {
    window.localStorage.removeItem("jwt")
    window.localStorage.removeItem(permissionsStorage)
  
    router.push("/login")
  }

  function doesExist(name: string): boolean {
    return window.localStorage.getItem(name) !== null
  }

  function isAllowed(permission: number): boolean {
    return doesExist(permissionsStorage) !== null && (Number(localStorage.getItem(permissionsStorage)) & permission) > 0
  }

  return (
    <div className="flex flex-row justify-between px-6 py-8 align-middle border-b-2">
      <div>
        <button onClick={() => router.push("/home")} className="text-2xl px-6 py-4">User Managment</button>
      </div>
      <div className="flex flex-row justify-between gap-9 text-xl">
        {isAllowed(UserPermissionsInt.CanPlaceOrder)  && <button className="hover:bg-slate-100 px-6 py-4 rounded-xl transition-all duration-200" onClick={() => router.push("/errormessage")}>Error Message</button>}
        {isAllowed(UserPermissionsInt.CanDeleteUsers) && <button className="hover:bg-slate-100 px-6 py-4 rounded-xl transition-all duration-200" onClick={() => router.push("/dish/new")}>New Dish</button>}
        {doesExist("jwt")                             && <button className="hover:bg-slate-100 px-6 py-4 rounded-xl transition-all duration-200" onClick={() => router.push("/orders/new")}>New Order</button>}
        {doesExist("jwt")                             && <button className="hover:bg-slate-100 px-6 py-4 rounded-xl transition-all duration-200" onClick={() => router.push("/orders")}>My Orders</button>}
        {!doesExist("jwt")                            && <button className="hover:bg-slate-100 px-6 py-4 rounded-xl transition-all duration-200" onClick={() => router.push("/login")}>Login</button>}
        {isAllowed(UserPermissionsInt.CanReadUsers)   && <button className="hover:bg-slate-100 px-6 py-4 rounded-xl transition-all duration-200" onClick={() => router.push("/users")}>Users</button>}
        {isAllowed(UserPermissionsInt.CanCreateUsers) && <button className="hover:bg-slate-100 px-6 py-4 rounded-xl transition-all duration-200" onClick={() => router.push("/users/new")}>New User</button>}
        {doesExist("jwt")                             && <button className="hover:bg-slate-100 px-6 py-4 rounded-xl transition-all duration-200" onClick={() => handleLogout()}>Logout</button>}
      </div>
    </div>
  )
}