"use client"
import { UserPermissions } from "@/types/user";
import { useRouter } from "next/navigation"

export default function Header() {
  
  const router = useRouter();
  
  function handleLogout(): void {
    localStorage.removeItem("jwt")
    localStorage.removeItem(UserPermissions.CanCreateUsers)
    localStorage.removeItem(UserPermissions.CanDeleteUsers)
    localStorage.removeItem(UserPermissions.CanReadUsers)
    localStorage.removeItem(UserPermissions.CanUpdateUsers)
  
    router.push("/login")
  }

  function doesExist(name: string): boolean {
    return localStorage.getItem(name) !== null
  }

  function isAllowed(permission: string): boolean {
    return doesExist(permission) !== null && localStorage.getItem(permission) === 'true'
  }

  return (
    <div className="flex flex-row justify-between px-6 py-8 align-middle">
      <div>
        <button onClick={() => router.push("/home")} className="text-2xl">User Managment</button>
      </div>
      <div className="flex flex-row justify-between gap-9 text-xl">
        {!doesExist("jwt")                         && <button onClick={() => router.push("/login")}>Login</button>}
        {isAllowed(UserPermissions.CanReadUsers)   && <button onClick={() => router.push("/users")}>Users</button>}
        {isAllowed(UserPermissions.CanCreateUsers) && <button onClick={() => router.push("/users/new")}>New User</button>}
        {doesExist("jwt")                          && <button onClick={() => handleLogout()}>Logout</button>}
      </div>
    </div>
  )
}