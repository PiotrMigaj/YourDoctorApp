import { AddressDto, CreateAddressRequest } from "./address";
import { CreateSpecializationRequest } from "./specialization";

export type DoctorDto = {
    id: number | null,
    firstName: string,
    lastName: string,
    email: string,
    phoneNumber: string,
    specializations: string[],
    addresses: AddressDto[],
}

export type DoctorForUserDto = {
    id: number | null,
    firstName: string,
    lastName: string,
    email: string,
    phoneNumber: string,
    specializations: string[],
}

export type CreateDoctorRequest = {
    email: string,
    password: string,
    firstName: string,
    lastName: string,
    phoneNumber: string,
    createSpecializationRequest:CreateSpecializationRequest,
    createAddressRequest:CreateAddressRequest,
}
