export function checkStatusCode(res: Response, badRequestMessage: string = "Invalid data"): Promise<any> | void {
  if (res.ok)
    return res.json()
  if (res.status === 403)
    throw Error("Incorrect email or password")

  if (res.status === 400)
    throw Error(badRequestMessage)

  if (res.status >= 500)
    throw Error("Server error")
}