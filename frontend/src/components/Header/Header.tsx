export default function Header() {
  return (
    <div className="flex flex-row justify-between px-6 py-8 align-middle">
      <div>
        <h3 className="text-2xl">User Managment</h3>
      </div>
      <div className="flex flex-row justify-between gap-9 text-xl">
        <h4>Login</h4>
        <h4>Users</h4>
        <h4>New User</h4>
        <h4>Logout</h4>
      </div>
    </div>
  )
}