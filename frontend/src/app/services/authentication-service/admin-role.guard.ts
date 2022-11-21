import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AdminRoleGuard implements CanActivate {

  constructor(private authService:AuthenticationService,private router:Router){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      
      let userId:number = +route.paramMap.get('id')!;
      console.log(userId);

      if(this.authService.isDoctor()&&this.authService.getUserId()==userId){
        // console.log(this.authService.getUserId());
        return true
      }
      this.router.navigate(['/home'])
      return false
  }

}
