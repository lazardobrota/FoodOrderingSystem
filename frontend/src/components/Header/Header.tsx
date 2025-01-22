"use client"
import { HeaderNav } from "@/types/navigation";
import { permissionsStorage, UserPermissions, UserPermissionsInt } from "@/types/user";
import { useRouter } from "next/navigation"
import { NavigationMenu, NavigationMenuContent, NavigationMenuItem, NavigationMenuLink, NavigationMenuList, NavigationMenuTrigger, navigationMenuTriggerStyle } from "../ui/navigation-menu";
import React from "react";
import { cn } from "@/lib/utils";
import Link from "next/link";

export default function Header() {

  const router = useRouter();

  const orders: HeaderNav[] = [
    {
      title: "All Orders",
      href: "/orders",
      description: "Show all already made orders",
      isActive: isAllowed(UserPermissionsInt.CanTrackOrder)
    },
    {
      title: "New Order",
      href: "/orders/new",
      description: "Make a new order with given dishes",
      isActive: doesExist("jwt")
    },
    {
      title: "Error Message",
      href: "/errormessage",
      description: "Information about orders that were invalid",
      isActive: isAllowed(UserPermissionsInt.CanPlaceOrder)
    },
    {
      title: "New Dish",
      href: "/dish/new",
      description: "Make a new dish",
      isActive: isAllowed(UserPermissionsInt.CanDeleteUsers)
    },
  ]

  const users: HeaderNav[] = [
    {
      title: "All Users",
      href: "/users",
      description: "Show all accounts on the site",
      isActive: isAllowed(UserPermissionsInt.CanReadUsers)
    },
    {
      title: "New Users",
      href: "/users/new",
      description: "Make a new account for user",
      isActive: isAllowed(UserPermissionsInt.CanCreateUsers)
    },
  ]

  const components: HeaderNav[] = [
    {
      title: "Login",
      href: "/login",
      description: "Login into the site to get your authorizations",
      isActive: undefined
    },
    {
      title: "Logout",
      href: "/login",
      description: "Logout from site",
      isActive: undefined
    }
  ]

  function doesExist(name: string): boolean {
    return window.localStorage.getItem(name) !== null
  }

  function isAllowed(permission: number): boolean {
    return doesExist(permissionsStorage) !== null && (Number(localStorage.getItem(permissionsStorage)) & permission) > 0
  }

  function handleLogout(): void {
    window.localStorage.removeItem("jwt")
    window.localStorage.removeItem(permissionsStorage)

    router.push("/login")
  }

  return (
    <header className="flex flex-row justify-between px-6 py-3 align-middle border-b border-gray-200">
      <div>
        <button onClick={() => router.push("/home")} className="text-xl px-6 py-4">Foodie</button>
      </div>
      <nav className="flex flex-row justify-between gap-9 text-xl">
        <NavigationMenu>
          <NavigationMenuList>

            <NavigationMenuItem>
              <NavigationMenuTrigger>Orders</NavigationMenuTrigger>
              <NavigationMenuContent>
                <ul className="grid gap-3 p-4 grid-cols-2 md:w-[400px] lg:w-[500px]">
                  {orders.map((value, index) => (
                    value.isActive &&
                    <ListItem key={index} href={value.href} title={value.title}>
                      {value.description}
                    </ListItem>
                  ))}
                </ul>
              </NavigationMenuContent>
            </NavigationMenuItem>

            <NavigationMenuItem>
              <NavigationMenuTrigger>Users</NavigationMenuTrigger>
              <NavigationMenuContent>
                <ul className="grid gap-3 p-4 grid-cols-2 md:w-[400px] lg:w-[500px]">
                  {users.map((value, index) => (
                    value.isActive &&
                    <ListItem key={index} href={value.href} title={value.title}>
                      {value.description}
                    </ListItem>
                  ))}
                </ul>
              </NavigationMenuContent>
            </NavigationMenuItem>

            {!doesExist("jwt") &&
              <NavigationMenuItem>
                <Link href="/login" legacyBehavior passHref>
                  <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                    Login
                  </NavigationMenuLink>
                </Link>
              </NavigationMenuItem>
            }
            {doesExist("jwt") &&
              <NavigationMenuItem>
                <NavigationMenuLink href="/login" onClick={_ => handleLogout()} className={navigationMenuTriggerStyle()}>
                  Logout
                </NavigationMenuLink>
              </NavigationMenuItem>
            }
          </NavigationMenuList>
        </NavigationMenu>
      </nav>
    </header>
  )
}

const ListItem = React.forwardRef<
  React.ElementRef<"a">,
  React.ComponentPropsWithoutRef<"a">
>(({ className, title, children, ...props }, ref) => {
  return (
    <li>
      <NavigationMenuLink asChild>
        <a
          ref={ref}
          className={cn(
            "block select-none space-y-1 rounded-md p-3 leading-none no-underline outline-none transition-colors hover:bg-slate-100 hover:text-accent-foreground focus:bg-accent focus:text-accent-foreground h-full",
            className
          )}
          {...props}
        >
          <div className="text-sm font-medium leading-none">{title}</div>
          <p className="line-clamp-2 text-sm leading-snug text-muted-foreground">
            {children}
          </p>
        </a>
      </NavigationMenuLink>
    </li>
  )
})
ListItem.displayName = "ListItem"