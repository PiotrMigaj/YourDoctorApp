import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CreateDoctorRequest } from 'src/app/models/doctor';
import { DoctorService } from 'src/app/services/doctor-service/doctor.service';

@Component({
  selector: 'app-doctor-registration-form',
  templateUrl: './doctor-registration-form.component.html',
  styleUrls: ['./doctor-registration-form.component.css']
})
export class DoctorRegistrationFormComponent implements OnInit {

  @ViewChild('ref') child: ElementRef | any;

  createDoctorRequest: CreateDoctorRequest;
  sendingDoctor: boolean = false;
  notification: string | any = null;
  specializations:string[]=[]

  constructor(private renderer: Renderer2, private doctorService: DoctorService, private snackBar: MatSnackBar, private router: Router) {
    this.createDoctorRequest = doctorService.getDefautCreateDoctorRequest();
  }

  ngOnInit(): void {
    this.doctorService.getSpecializationsFromBackend()
    .subscribe({
      next: (data) => {
        this.specializations = data
        console.log(data)
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

  registerDoctor(): void {
    this.sendingDoctor = true;
    this.doctorService.registerDoctorInBackend(this.createDoctorRequest)
      .subscribe({
        next: (data) => {
          this.sendingDoctor = false;
          this.snackBar.open('Doctor has been added', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 5000
          })
          this.router.navigate(['/login'])
          console.log(data)
        },
        error: (error: any) => {
          this.sendingDoctor = false;
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
    this.createDoctorRequest = this.doctorService.getDefautCreateDoctorRequest();
  }

}
