export type AddressDto = {
    id: number | null,
    city: string,
    zipCode: string,
    street: string,
    number: string,
}

export type CreateAddressRequest = {
    city: string,
    zipCode: string,
    street: string,
    number: string,
}