import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BACKEND_BASE_URL } from 'src/app/models/const';
import { AppUserDto, CreateAppUserRequest } from 'src/app/models/user';

@Injectable({
  providedIn: 'root'
})
export class AppUserService {

  loadingList: boolean = false;

  constructor(private httpClient: HttpClient) { }

  public getDefautCreateAppUserRequest(): CreateAppUserRequest {
    return {
      email: '',
      password: '',
      firstName: '',
      lastName: '',
    }
  }

  public registerAppUserInBackend(createAppUserRequest:CreateAppUserRequest):Observable<AppUserDto>{
    return this.httpClient.post<AppUserDto>(BACKEND_BASE_URL+'user',createAppUserRequest);
  }

  public getAppUserByIdFromBackend(userId:number|null):Observable<AppUserDto>{
    return this.httpClient.get<AppUserDto>(BACKEND_BASE_URL+'user/'+userId);
  }
}
