export class UpdateUser {
  id: number = 0
  name: string = ""
  lastname: string = ""
  email: string = ""
  password: string = ""
  permissions: Record<string, boolean> = {}

}

export class User {
  id: number = 0
  name: string = ""
  lastname: string = ""
  email: string = ""
  permissions: Record<string, boolean> = {}
}

export enum UserPermissions {
  CanReadUsers   = "can_read_users",
  CanCreateUsers = "can_create_users",
  CanUpdateUsers = "can_update_users",
  CanDeleteUsers = "can_delete_users",
}