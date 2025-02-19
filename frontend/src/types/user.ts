export class UpdateUser {
  id: number = 0
  name: string = ""
  lastname: string = ""
  email: string = ""
  password: string = ""
  permissions: Record<string, boolean> = {
    [UserPermissions.CanDeleteUsers]: false,
    [UserPermissions.CanUpdateUsers]: false,
    [UserPermissions.CanReadUsers]: false,
    [UserPermissions.CanCreateUsers]: false,
  }

}

export class UserLogin {
  email: string = ""
  password: string = ""
}

export class User {
  id: number = 0
  name: string = ""
  lastname: string = ""
  email: string = ""
  permissions: Record<string, boolean> = {}
}

export enum UserPermissions {
  CanReadUsers     = "can_read_users",
  CanCreateUsers   = "can_create_users",
  CanUpdateUsers   = "can_update_users",
  CanDeleteUsers   = "can_delete_users",
  CanSearchOrder   = "can_search_order",
  CanPlaceOrder    = "can_place_order",
  CanCancelOrder   = "can_cancel_order",
  CanTrackOrder    = "can_track_order",
  CanScheduleOrder = "can_schedule_order",
}

export enum UserPermissionsInt {
  CanReadUsers     = 1 << 0,
  CanCreateUsers   = 1 << 1,
  CanUpdateUsers   = 1 << 2,
  CanDeleteUsers   = 1 << 3,
  CanSearchOrder   = 1 << 4,
  CanPlaceOrder    = 1 << 5,
  CanCancelOrder   = 1 << 6,
  CanTrackOrder    = 1 << 7,
  CanScheduleOrder = 1 << 8
}

export const permissionsStorage = "permissions"