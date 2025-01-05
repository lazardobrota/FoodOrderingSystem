"use client"

import Header from "@/components/Header/Header";
import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { usePermissionCheck } from "@/hooks/credentials";
import { User, UserPermissions } from "@/types/user";
import { useRouter } from "next/navigation";
import { useState, useEffect, MouseEvent } from "react";

export default function Orders() {
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
    fetch(`http://localhost:8090/user/all?page=${page}&size=${size}`, {
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

      <div className="px-32 flex flex-col gap-4">

        <div>
          <Button>Create Order</Button>
        </div>

        <div className="flex flex-col gap-6">
          {/* TODO Make this grid */}
          <div className="flex flex-col gap-4">
            {/* TODO Make this grid */}
            <div className="flex flex-row place-items-center gap-5 max-w-80">
              <Label>User Id:</Label>
              <Input />
            </div>
            <div className="flex flex-row place-items-center gap-5 max-w-80">
              <Label>Date from:</Label>
              <Input />
            </div>
            <div className="flex flex-row place-items-center gap-5 max-w-80">
              <Label>Date to:</Label>
              <Input />
            </div>
            <div className="flex flex-row place-items-center gap-5 max-w-80">
              <Checkbox id="ordered" />
              <Label htmlFor="ordered">Ordered</Label>
            </div>
            <div className="flex flex-row place-items-center gap-5 max-w-80">
              <Checkbox id="preparing" />
              <Label htmlFor="preparing">Preparing</Label>
            </div>
            <div className="flex flex-row place-items-center gap-5 max-w-80">
              <Checkbox id="in_delivery" />
              <Label htmlFor="in_delivery">In Delivery</Label>
            </div>
            <div className="flex flex-row place-items-center gap-5 max-w-80">
              <Checkbox id="delivered" />
              <Label htmlFor="delivered">Delivered</Label>
            </div>
            <div className="flex flex-row place-items-center gap-5 max-w-80">
              <Checkbox id="canceled" />
              <Label htmlFor="canceled">Canceled</Label>
            </div>
          </div>
          <div>
            <Button>Search</Button>
          </div>

          <Table>
            <TableCaption>Users</TableCaption>
            <TableHeader className="p-10">
              <TableRow className="hover:bg-white">
                <TableHead>Name</TableHead>
                <TableHead>Lastname</TableHead>
                <TableHead>Email</TableHead>
                <TableHead>Permissions</TableHead>
                {isAllowed(UserPermissions.CanDeleteUsers) && <TableHead >Delete</TableHead>}
              </TableRow>
            </TableHeader>
            <TableBody>
              {users.map((user: User) => (
                <TableRow key={user.id} className="hover:cursor-pointer">
                  <TableCell onClick={() => handleRowPress(user.id)}>{user.name}</TableCell>
                  <TableCell onClick={() => handleRowPress(user.id)}>{user.lastname}</TableCell>
                  <TableCell onClick={() => handleRowPress(user.id)}>{user.email}</TableCell>
                  <TableCell onClick={() => handleRowPress(user.id)}>{
                    Object.entries(user.permissions).filter(([_, value]) => value === true).map(([key]) => (
                      <label key={key} className="m-2 py-2 px-3 bg-slate-400 text-white rounded-2xl hover:cursor-pointer">{key}</label>
                    ))
                  }</TableCell>
                  {isAllowed(UserPermissions.CanDeleteUsers) && <TableCell><button className="bg-red-700 hover:bg-red-800 px-4 py-2 rounded-full text-white" onClick={() => handleDeleteUser(user.id)}>Cancel</button></TableCell>}
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </div>

        <div className="flex flex-row gap-3">
          <button onClick={(e) => handleBefore(e)} className="bg-slate-300 p-2 rounded-lg hover:bg-slate-400">Before</button>
          <button onClick={(e) => handleNext(e)} className="bg-slate-300 p-2 rounded-lg hover:bg-slate-400">Next</button>
        </div>
      </div>

    </div>
  )
}