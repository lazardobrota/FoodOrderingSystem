"use client"

import Header from "@/components/Header/Header"
import { Badge } from "@/components/ui/badge"
import { Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { usePermissionCheck } from "@/hooks/credentials"
import { ErrorMessage } from "@/types/errormessage"
import { permissionsStorage, User, UserPermissions, UserPermissionsInt } from "@/types/user"
import { useRouter } from "next/navigation"
import { MouseEvent, useEffect, useState } from "react"

export default function ErrorMessages() {

  const [errorMessages, setErrorMessages] = useState<ErrorMessage[]>([])
  const [page, setPage] = useState<number>(0);
  const [last, setLast] = useState<boolean>(false);

  const size: number = 5;
  const router = useRouter();

  usePermissionCheck(UserPermissionsInt.CanPlaceOrder)

  useEffect(() => {
    restCallErrorMessages(page, size);
  }, [])

  function restCallErrorMessages(page: number, size: number): void {
    fetch(`http://localhost:8090/error?page=${page}&size=${size}`, {
      method: "GET",
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => {
        setErrorMessages(data.content)
        setLast(data.last)
      })
  }



  function handleBefore(e: MouseEvent<HTMLButtonElement, globalThis.MouseEvent>): void {
    e.preventDefault()

    if (page == 0)
      return;

    restCallErrorMessages(page - 1, size);
    setPage(page - 1);
  }

  function handleNext(e: MouseEvent<HTMLButtonElement, globalThis.MouseEvent>): void {
    e.preventDefault()

    if (last)
      return;

    restCallErrorMessages(page + 1, size);
    setPage(page + 1);
  }

  return (
    <div className="flex flex-col gap-14">
      <Header></Header>

      <div className="px-32">
        <div>
          <Table>
            <TableCaption>Users</TableCaption>
            <TableHeader className="p-10">
              <TableRow className="hover:bg-white">
                <TableHead>Email</TableHead>
                <TableHead>Status</TableHead>
                <TableHead>Date</TableHead>
                <TableHead>Message</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {errorMessages.map((errorMessage: ErrorMessage, index) => (
                <TableRow key={index}>
                  <TableCell>{errorMessage.user.email}</TableCell>
                  <TableCell>{errorMessage.status}</TableCell>
                  <TableCell>{errorMessage.date}</TableCell>
                  <TableCell>{errorMessage.message}</TableCell>
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