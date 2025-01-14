import { permissionsStorage, UserPermissions } from "@/types/user"
import { useRouter } from "next/navigation"
import { useEffect } from "react"

export function usePermissionCheck(permission: number) {

  const router = useRouter()

  useEffect(() => {
    const localPermission: number | null = Number(window.localStorage.getItem(permissionsStorage)) & permission
    if (localPermission === null || localPermission === 0)
      router.push("/invalid")
  }, [permission, router])
}