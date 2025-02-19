"use client"

import Header from "@/components/Header/Header";
import { DateTimePicker24h } from "@/components/my-ui/datepicker";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { usePermissionCheck } from "@/hooks/credentials";
import { Dish } from "@/types/dish";
import { Order, OrderStatus, OrderStatusInt, SearchParams, UpdateOrderStatus } from "@/types/order";
import { permissionsStorage, UserPermissions, UserPermissionsInt } from "@/types/user";
import { useRouter } from "next/navigation";
import { MouseEvent, useEffect, useState } from "react";
import { CiCircleCheck, CiCircleRemove } from "react-icons/ci";
import { StompSessionProvider, useSubscription } from "react-stomp-hooks";

export default function OrdersSocket() {
  return (
    <StompSessionProvider url={"http://localhost:8090/food-backend"}>
      <Orders></Orders>
    </StompSessionProvider>
  )
}

export function Orders() {
  const [orders, setOrders] = useState<Order[]>([])
  const [page, setPage] = useState<number>(0);
  const [isLastPage, setLastPage] = useState<boolean>(false);
  const [searchParams, setSearchParams] = useState<SearchParams>(new SearchParams());

  const size: number = 5;
  const router = useRouter();

  const orderStatuses: Record<string, number> = {
    [OrderStatus.ORDERED]: OrderStatusInt.ORDERED,
    [OrderStatus.PREPARING]: OrderStatusInt.PREPARING,
    [OrderStatus.IN_DELIVERY]: OrderStatusInt.IN_DELIVERY,
    [OrderStatus.DELIVERED]: OrderStatusInt.DELIVERED,
    [OrderStatus.CANCEL]: OrderStatusInt.CANCEL,
  }

  usePermissionCheck(UserPermissionsInt.CanTrackOrder)

  useSubscription('/order/status', (message) => {
    const status: UpdateOrderStatus = JSON.parse(message.body)
    setOrders((prevOrders) => 
      prevOrders.map((order: Order) =>
        order.id === status.orderId ? {...order, status: status.orderStatus, active: status.active} : order
      )
    )
  });

  useEffect(() => {
    restCallOrders(page, size);
  }, [])

  function restCallOrders(page: number, size: number): void {
    fetch(`http://localhost:8090/order?page=${page}&size=${size}&user_email=${searchParams.userEmail}&start_date=${searchParams.startDate}&end_date=${searchParams.endDate}&status=${searchParams.status}`, {
      method: "GET",
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(res => res.json())
      .then(data => {
        setOrders(data.content)
        setPage(0);
        setLastPage(data.last)
      })
  }

  function groupDishes(dishes: Dish[]): { dish: Dish; amount: number }[] {
    const itemMap = new Map<number, { dish: Dish; amount: number }>();

    dishes.forEach((dish) => {
      if (itemMap.has(dish.id)) {
        itemMap.get(dish.id)!.amount++;
      } else {
        // Add a new entry for the item
        itemMap.set(dish.id, { dish, amount: 1 });
      }
    });

    return Array.from(itemMap.values());
  }



  function handleBefore(e: MouseEvent<HTMLButtonElement, globalThis.MouseEvent>): void {
    e.preventDefault()

    if (page == 0)
      return;

    restCallOrders(page - 1, size);
    setPage(page - 1);
  }

  function handleNext(e: MouseEvent<HTMLButtonElement, globalThis.MouseEvent>): void {
    e.preventDefault()

    if (isLastPage)
      return;

    restCallOrders(page + 1, size);
    setPage(page + 1);
  }

  function handleDeleteOrder(id: number): void {
    fetch(`http://localhost:8090/order/${id}`, {
      method: "DELETE",
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('jwt')
      }
    })
      .then(() => restCallOrders(page, size))
  }

  function isAllowed(permission: number): boolean {
    return localStorage.getItem(permissionsStorage) !== null && (Number(localStorage.getItem(permissionsStorage)) & permission) > 0
  }

  return (
    <div className="flex flex-col gap-14">
      <Header></Header>

      <div className="px-32 flex flex-col gap-4">
        <div className="flex flex-col gap-6">
          <div className="flex flex-row gap-4 justify-between">
            {isAllowed(UserPermissionsInt.CanSearchOrder) &&
              <div className="flex flex-row place-items-center gap-5 max-w-80">
                <Label>User Email:</Label>
                <Input name="email" value={searchParams.userEmail} onChange={e => setSearchParams({ ...searchParams, userEmail: e.target.value })} />
              </div>}
            <div className="flex flex-row place-items-center gap-5 max-w-80">
              <Label>Date from:</Label>
              <Input name="date_from" value={searchParams.startDate} onChange={e => setSearchParams({ ...searchParams, startDate: e.target.value })} />
              {/* <DateTimePicker24h/> */}
            </div>
            <div className="flex flex-row place-items-center gap-5 max-w-80">
              <Label>Date to:</Label>
              <Input name="date_to" value={searchParams.endDate} onChange={e => setSearchParams({ ...searchParams, endDate: e.target.value })} />
            </div>
            {Object.entries(orderStatuses).map(([key, value]) => (
              <div key={key} className="flex flex-row place-items-center gap-5 max-w-80">
                <Checkbox id={key} onClick={() => setSearchParams({ ...searchParams, status: searchParams.status ^ value })} />
                <Label htmlFor={key}>{key.toUpperCase().replaceAll("_", " ")}</Label>
              </div>))
            }
            <div>
              <Button onClick={() => restCallOrders(0, size)}>Search</Button>
            </div>
          </div>

          <Table>
            <TableCaption>Users</TableCaption>
            <TableHeader className="p-10">
              <TableRow className="hover:bg-white">
                <TableHead>Email</TableHead>
                <TableHead>Status</TableHead>
                <TableHead>Active</TableHead>
                <TableHead>Dishes</TableHead>
                <TableHead>Scheduled</TableHead>
                {isAllowed(UserPermissionsInt.CanDeleteUsers) && <TableHead >Action</TableHead>}
              </TableRow>
            </TableHeader>
            <TableBody>
              {orders.map((order: Order) => (
                <TableRow key={order.id}>
                  {isAllowed(UserPermissionsInt.CanDeleteUsers) && <TableCell>{order.createdBy.email}</TableCell>}
                  <TableCell>{order.status.replaceAll("_", " ")}</TableCell>
                  <TableCell>{order.active ? <CiCircleCheck className="text-green-600 size-6" /> : <CiCircleRemove className="text-red-600 size-6" />}</TableCell>
                  <TableCell>{
                    groupDishes(order.dishes).map(({ dish, amount }, index) => (
                      <Badge className="m-1" key={index}> {amount} | {dish.name}</Badge>
                    ))
                  }</TableCell>
                  <TableCell>{order.createdDate}</TableCell>
                  {isAllowed(UserPermissionsInt.CanDeleteUsers) && order.status === OrderStatus.ORDERED && order.active &&
                    <TableCell><Button className="bg-red-700 hover:bg-red-800" onClick={() => handleDeleteOrder(order.id)}>Cancel</Button></TableCell>}
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </div>

        <div className="flex flex-row gap-3">
          <Button onClick={(e) => handleBefore(e)} className="bg-slate-300 p-2 rounded-lg hover:bg-slate-400">Before</Button>
          <Button onClick={(e) => handleNext(e)} className="bg-slate-300 p-2 rounded-lg hover:bg-slate-400">Next</Button>
        </div>
      </div>

    </div>
  )
}