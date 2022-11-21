export type AuthenticationRequest = {
    login: string,
    pass: string
}

export type SecurityUserDto = {
    id: number | null,
    email: string,
    roles: string[]
}

export type CreateAppUserRequest = {
    email: string,
    password: string,
    firstName: string,
    lastName: string,
}

export type AppUserDto = {
    id:number,
    firstName: string,
    lastName: string,
    email: string,
}
