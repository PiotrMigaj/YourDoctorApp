import { AddressDto } from "./address"
import { DoctorForUserDto } from "./doctor"
import { AppUserDto } from "./user"

export type AppointmentDto = {
  id: number | null,
  dateOfAppointment: string,
  timeOfAppointment: string,
  doctor: DoctorForUserDto,
  address: AddressDto,
  taken: boolean,
}

export type CreateAppointmentRequest = {
  doctorId:number,
  addressId:number,
  dateOfAppointment: string,
  timeOfAppointment: string,
}

export type CreateAppointmentRegistrationRequest = {
  appointmentId:number,
  userId:number|null,
}

export type AppointmentForUserDto = {
  id: number | null,
  dateOfAppointment: string,
  timeOfAppointment: string,
  doctor: DoctorForUserDto,
  address: AddressDto,
  taken: boolean,
}

export type AppointmentForDoctorDto = {
  id: number | null,
  dateOfAppointment: string,
  timeOfAppointment: string,
  user: AppUserDto,
  address: AddressDto,
  taken: boolean,
}
