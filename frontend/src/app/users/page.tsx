import Header from "@/components/Header/Header"

export default function Users() {
  return (
    <div className="flex flex-col gap-14">
      <Header></Header>
      <table className="table-auto border-collapse border-spacing-2 text-center">
        <caption className="caption-top text-gray-600 p-10">
          Users
        </caption>
        <thead>
          <tr className="divide-x-4">
            <th>Name</th>
            <th>Lastname</th>
            <th>Email</th>
            <th>Permissions</th>
          </tr>
        </thead>
        <tbody>
          <tr className="divide-x-4">
            <td>Lazar</td>
            <td>Dobrota</td>
            <td>ldobrota7421rn@raf.rs</td>
            <td>One Two Tree</td>
            <td><button className="bg-red-700 px-4 py-2 rounded-full text-white">Delete</button></td>
          </tr>
          <tr className="divide-x-4">
            <td>Lazar</td>
            <td>Dobrota</td>
            <td>ldobrota7421rn@raf.rs</td>
            <td>One Two Tree</td>
            <td><button className="bg-red-700 px-4 py-2 rounded-full text-white">Delete</button></td>
          </tr>
          <tr className="divide-x-4">
            <td>Lazar</td>
            <td>Dobrota</td>
            <td>ldobrota7421rn@raf.rs</td>
            <td>One Two Tree</td>
            <td><button className="bg-red-700 px-4 py-2 rounded-full text-white">Delete</button></td>
          </tr>
          <tr className="divide-x-4">
            <td>Lazar</td>
            <td>Dobrota</td>
            <td>ldobrota7421rn@raf.rs</td>
            <td>One Two Tree</td>
            <td><button className="bg-red-700 px-4 py-2 rounded-full text-white">Delete</button></td>
          </tr>
        </tbody>
      </table>
    </div>
  )
}