import { UserPermissions } from "@/types/user"
import { useRouter } from "next/navigation"
import { useEffect } from "react"

export function usePermissionCheck(permission: string) {

  const router = useRouter()

  useEffect(() => {
    const localPermission: string | null = window.localStorage.getItem(permission)
    if (localPermission === null || localPermission !== "true")
      router.push("/invalid")
  }, [permission, router])
}