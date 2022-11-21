import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppointmentDto, AppointmentForDoctorDto, AppointmentForUserDto, CreateAppointmentRegistrationRequest, CreateAppointmentRequest } from 'src/app/models/appointment';
import { BACKEND_BASE_URL } from 'src/app/models/const';
import { PageResponse } from 'src/app/models/pagination';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private httpClient: HttpClient) { }

  public getAppointmentsForDoctorFromBackend(doctorId:number|null,page?: number | null, size?: number | null): Observable<PageResponse<AppointmentForDoctorDto>> {

    let params = {
      page: (page ? page : 0),
      size: (size ? size : 10),
    }

    return this.httpClient.get<PageResponse<AppointmentForDoctorDto>>(BACKEND_BASE_URL + `appointments/doctor/${doctorId}`, { params: params });
  }

  public getAppointmentsForUserFromBackend(userId:number|null,page?: number | null, size?: number | null): Observable<PageResponse<AppointmentForUserDto>> {

    let params = {
      page: (page ? page : 0),
      size: (size ? size : 10),
    }

    return this.httpClient.get<PageResponse<AppointmentForUserDto>>(BACKEND_BASE_URL + `appointments/user/${userId}`, { params: params });
  }

  public getAppointmentsForUsersWithDoctorIdFromBackend(doctorId:number|null,page?: number | null, size?: number | null): Observable<PageResponse<AppointmentForDoctorDto>> {

    let params = {
      page: (page ? page : 0),
      size: (size ? size : 10),
    }

    return this.httpClient.get<PageResponse<AppointmentForDoctorDto>>(BACKEND_BASE_URL + `appointments/doctor/${doctorId}/make-appointment`, { params: params });
  }

  public getDefautCreateAppointmentRequest(): CreateAppointmentRequest {
    return {
      doctorId: 0,
      addressId: 0,
      dateOfAppointment: '',
      timeOfAppointment: '',
    }
  }

  public registerAppointmentInBackend(createAppointmentRequest: CreateAppointmentRequest):Observable<AppointmentDto> {
    return this.httpClient.post<AppointmentDto>(BACKEND_BASE_URL+'appointments',createAppointmentRequest);
  }

  public registerUserForAppointment(userId: number | null, appointmentId: number):Observable<AppointmentDto> {
    let createAppointmentRegistrationRequest:CreateAppointmentRegistrationRequest = {
      appointmentId: appointmentId,
      userId: userId
    }
    return this.httpClient.patch<AppointmentDto>(BACKEND_BASE_URL+`appointments/make-appointment`,createAppointmentRegistrationRequest);
  }



}
