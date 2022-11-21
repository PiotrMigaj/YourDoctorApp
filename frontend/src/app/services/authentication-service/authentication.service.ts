import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { LOCAL_STORAGE_AUTH_TOKEN, LOCAL_STORAGE_AUTH_USER } from 'src/app/models/const';
import { AuthenticationRequest, SecurityUserDto } from 'src/app/models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  authorizationHeader: string | null = null;
  loggedInUser: SecurityUserDto | null = null;
  loggingIn: boolean = false;

  constructor(private router: Router, private httpClient:HttpClient) { }

  logout(){
    localStorage.removeItem(LOCAL_STORAGE_AUTH_TOKEN)
    localStorage.removeItem(LOCAL_STORAGE_AUTH_USER)
    this.authorizationHeader = null
    this.loggedInUser = null
    this.router.navigate([''])
  }

  authenticate(request: AuthenticationRequest): void {
    this.loggingIn = true;

    this.httpClient.post<SecurityUserDto>("http://localhost:8080/auth/login", request,
      {
        observe: 'response',
        withCredentials: false,
      })
      .subscribe({
        next: (data) => {
          console.log("Success logging in!")
          const authorizationHeader = data.headers.get('Authorization');
          this.authorizationHeader = authorizationHeader;
          this.loggedInUser = data.body;
          localStorage.setItem(LOCAL_STORAGE_AUTH_TOKEN, this.authorizationHeader!)
          localStorage.setItem(LOCAL_STORAGE_AUTH_USER, JSON.stringify(this.loggedInUser))
          console.log(this.loggedInUser)
          console.log(this.authorizationHeader)
          this.loggingIn = false;
          if(this.hasRole('ROLE_DOCTOR')&&!this.hasRole('ROLE_ADMIN')){
            this.router.navigate(['/doctor',this.loggedInUser?.id])
          }else{
            this.router.navigate(['/home'])
          }
          
        },
        error: (error) => {
          console.log('Error logging in: ')
          console.log(error)
          this.authorizationHeader = null;
          this.loggedInUser = null;
          localStorage.removeItem(LOCAL_STORAGE_AUTH_TOKEN)
          localStorage.removeItem(LOCAL_STORAGE_AUTH_USER)
          this.loggingIn = false;
        }
      })
  }

  // HELPERS

  hasRole(role: string): boolean {
    return this.loggedInUser != null && this.loggedInUser.roles.includes(role)
  }

  isSimpleUser():boolean{
    return this.loggedInUser != null && this.loggedInUser.roles.includes('ROLE_USER') && this.loggedInUser.roles.length===1
  }

  isDoctor():boolean{
    return this.loggedInUser != null && this.loggedInUser.roles.includes('ROLE_DOCTOR') && !this.loggedInUser.roles.includes('ROLE_ADMIN')
  }

  getUserId(): number | null {
    this.refreshAuthentication()
    if (this.loggedInUser != null) {
      return this.loggedInUser.id
    }
    return null
  }

  private refreshAuthentication(): void {
    if (this.authorizationHeader == null) {
      const token = localStorage.getItem(LOCAL_STORAGE_AUTH_TOKEN)
      if (token != null) {
        this.authorizationHeader = token;
        this.loggedInUser = JSON.parse(localStorage.getItem(LOCAL_STORAGE_AUTH_USER)!)
      }
    }
  }

  getAuthorizationHeader(): string | null {
    this.refreshAuthentication()
    return this.authorizationHeader
  }

  isLoggedIn(): boolean {
    return (this.getAuthorizationHeader() != null)
  }

}
