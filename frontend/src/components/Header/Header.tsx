"use client"
import { useRouter } from "next/navigation"

export default function Header() {
  
  const router = useRouter();
  
  function handleLogout(): void {
    window.localStorage.removeItem("jwt"); 
    //TODO remove all roles
    router.push("/login")
  }

  return (
    <div className="flex flex-row justify-between px-6 py-8 align-middle">
      <div>
        <h3 className="text-2xl">User Managment</h3>
      </div>
      <div className="flex flex-row justify-between gap-9 text-xl">
        <button onClick={() => router.push("/login")}>Login</button>
        <button onClick={() => router.push("/users")}>Users</button>
        <button onClick={() => router.push("/users/new")}>New User</button>
        <button onClick={() => handleLogout()}>Logout</button>
      </div>
    </div>
  )
}