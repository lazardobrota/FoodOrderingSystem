"use client"

import Header from "@/components/Header/Header"
import { Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { usePermissionCheck } from "@/hooks/credentials"
import { User, UserPermissions } from "@/types/user"
import { useRouter } from "next/navigation"
import { MouseEvent, useEffect, useState } from "react"

export default function Users() {

  const [users, setUser] = useState<User[]>([])
  const [page, setPage] = useState<number>(0);
  const [last, setLast] = useState<boolean>(false);

  const size: number = 5;
  const router = useRouter();

  usePermissionCheck(UserPermissions.CanReadUsers)

  useEffect(() => {
    restCallUsers(page, size);
  }, [])

  function restCallUsers(page: number, size: number): void {
    fetch(`http://localhost:8090/user?page=${page}&size=${size}`, {
      method: "GET",
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => {
        setUser(data.content)
        setLast(data.last)
      })
  }



  function handleBefore(e: MouseEvent<HTMLButtonElement, globalThis.MouseEvent>): void {
    e.preventDefault()

    if (page == 0)
      return;

    restCallUsers(page - 1, size);
    setPage(page - 1);
  }

  function handleNext(e: MouseEvent<HTMLButtonElement, globalThis.MouseEvent>): void {
    e.preventDefault()

    if (last)
      return;

    restCallUsers(page + 1, size);
    setPage(page + 1);
  }

  function handleRowPress(id: number): void {
    router.push(`/users/edit?id=${id}`)
  }

  function handleDeleteUser(id: number): void {
    fetch(`http://localhost:8090/user/${id}`, {
      method: "DELETE",
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(() => restCallUsers(page, size))
  }

  function isAllowed(permission: string): boolean {
    return localStorage.getItem(permission) !== null && localStorage.getItem(permission) === 'true'
  }


  if (users.length === 0)
    return;

  return (
    <div className="flex flex-col gap-14">
      <Header></Header>

      <Table>
        <TableCaption>Users</TableCaption>
        <TableHeader className="p-10">
          <TableRow>
            <TableHead >Name</TableHead>
            <TableHead>Lastname</TableHead>
            <TableHead>Email</TableHead>
            <TableHead className="w-1/2">Permissions</TableHead>
            {isAllowed(UserPermissions.CanDeleteUsers) && <TableHead >Delete</TableHead>}
          </TableRow>
        </TableHeader>
        <TableBody>
          {users.map((user: User) => (
            <TableRow key={user.id} className="hover:cursor-pointer">
              <TableCell className="p-4" onClick={() => handleRowPress(user.id)}>{user.name}</TableCell>
              <TableCell className="p-4" onClick={() => handleRowPress(user.id)}>{user.lastname}</TableCell>
              <TableCell className="p-4" onClick={() => handleRowPress(user.id)}>{user.email}</TableCell>
              <TableCell className="p-4" onClick={() => handleRowPress(user.id)}>{
                Object.entries(user.permissions).filter(([_, value]) => value === true).map(([key]) => (
                  <label key={key} className="m-2 py-2 px-3 bg-slate-400 text-white rounded-2xl hover:cursor-pointer">{key}</label>
                ))
              }</TableCell>
              {isAllowed(UserPermissions.CanDeleteUsers) && <TableCell><button className="bg-red-700 hover:bg-red-800 px-4 py-2 rounded-full text-white" onClick={() => handleDeleteUser(user.id)}>Delete</button></TableCell>}
            </TableRow>
          ))}
        </TableBody>
      </Table>

      <div className="flex flex-row gap-3 px-6">
        <button onClick={(e) => handleBefore(e)} className="bg-slate-300 p-2 rounded-lg hover:bg-slate-400">Before</button>
        <button onClick={(e) => handleNext(e)} className="bg-slate-300 p-2 rounded-lg hover:bg-slate-400">Next</button>
      </div>

    </div>
  )
}