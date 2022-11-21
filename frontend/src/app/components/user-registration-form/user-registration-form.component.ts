import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CreateAppUserRequest } from 'src/app/models/user';
import { AppUserService } from 'src/app/services/appuser-service/app-user.service';

@Component({
  selector: 'app-user-registration-form',
  templateUrl: './user-registration-form.component.html',
  styleUrls: ['./user-registration-form.component.css']
})
export class UserRegistrationFormComponent implements OnInit {

  @ViewChild('ref') child: ElementRef | any;

  createAppUserRequest: CreateAppUserRequest;
  sendingUser: boolean = false;
  notification: string | any = null;

  constructor(private renderer: Renderer2,private appUserService: AppUserService,private snackBar: MatSnackBar,private router: Router) {
      this.createAppUserRequest = appUserService.getDefautCreateAppUserRequest();
  }

  ngOnInit(): void {
  }

  registerUser(): void {
    this.sendingUser = true;
    this.appUserService.registerAppUserInBackend(this.createAppUserRequest)
      .subscribe({
        next: (data) => {
          this.sendingUser = false;
          this.snackBar.open('User has been added', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 5000
          })
          this.router.navigate(['/login'])
          console.log(data)
        },
        error: (error: any) => {
          this.sendingUser = false;
          this.notification = error.error.message
          setTimeout(() => {
            this.renderer.addClass(this.child.nativeElement, 'hidden');
            setTimeout(() => {
              this.notification = null;
            }, 1000)
          }, 3000)
        },
      }
      )
  }

  clearForm(): void {
    this.createAppUserRequest = this.appUserService.getDefautCreateAppUserRequest();
  }
 

}
