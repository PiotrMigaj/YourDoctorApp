import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { BACKEND_BASE_URL } from 'src/app/models/const';
import { CreateDoctorRequest, DoctorDto } from 'src/app/models/doctor';
import { PageResponse } from 'src/app/models/pagination';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {


  constructor(private router: Router, private httpClient: HttpClient) { }

  public getSpecializationsFromBackend(): Observable<string[]> {
    return this.httpClient.get<string[]>(BACKEND_BASE_URL + 'specialization');
  }

  public getDoctorFromBackendById(doctorId:number):Observable<DoctorDto>{
    return this.httpClient.get<DoctorDto>(BACKEND_BASE_URL+'doctor/'+doctorId);
  }

  public getRecentlyJoinedDoctors():Observable<DoctorDto[]>{
    return this.httpClient.get<DoctorDto[]>(BACKEND_BASE_URL+'doctor/recently-joined')
  }

  public getDoctorsFromBackend(city?: string, specialization?: string,page?: number | null, size?: number | null): Observable<PageResponse<DoctorDto>> {

    let params;

    if (city && specialization) {
      params = {
        city: city,
        specialization: specialization,
        page: (page ? page : 0),
        size: (size ? size : 10),
      }
    } else if (city && !specialization) {
      params = {
        city: city,
        page: (page ? page : 0),
        size: (size ? size : 10),
      }
    } else if (!city && specialization) {
      params = {
        specialization: specialization,
        page: (page ? page : 0),
        size: (size ? size : 10),
      }
    }else{
      params = {
        page: (page ? page : 0),
        size: (size ? size : 10),
      }
    }

    return this.httpClient.get<PageResponse<DoctorDto>>(BACKEND_BASE_URL + 'doctor', { params: params });
  }

  public registerDoctorInBackend(createDoctorRequest: CreateDoctorRequest):Observable<DoctorDto> {
    return this.httpClient.post<DoctorDto>(BACKEND_BASE_URL + 'doctor', createDoctorRequest);
  }

  public getDefautCreateDoctorRequest(): CreateDoctorRequest {
    return {
      email: '',
      password: '',
      firstName: '',
      lastName: '',
      phoneNumber: '',
      createSpecializationRequest: {
        specialization: ''
      },
      createAddressRequest: {
        city: '',
        zipCode: '',
        street: '',
        number: ''
      }
    }
  }
}
