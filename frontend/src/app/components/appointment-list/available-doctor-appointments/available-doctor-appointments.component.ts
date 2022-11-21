import { Component, ElementRef, Input, OnInit, Renderer2, ViewChild } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AppointmentForDoctorDto } from 'src/app/models/appointment';
import { AppointmentService } from 'src/app/services/appointment-service/appointment.service';
import { AuthenticationService } from 'src/app/services/authentication-service/authentication.service';

@Component({
  selector: 'app-available-doctor-appointments',
  templateUrl: './available-doctor-appointments.component.html',
  styleUrls: ['./available-doctor-appointments.component.css']
})
export class AvailableDoctorAppointmentsComponent implements OnInit {

  @ViewChild('ref') child: ElementRef | any;
  notification: string | any = null;
  loadingList: boolean = false;
  totalElements: number = 0;
  appointments: AppointmentForDoctorDto[] = [];
  @Input() doctorId!: number;

  dataSource = [
    'date-and-hour-of-appointment',
    'address',
    'register-button'
  ]

  defPageEvent:PageEvent={
    pageSize: 5,
    pageIndex: 0,
    length: 0
  }

  constructor(private snackBar: MatSnackBar, private renderer: Renderer2, private appointmentService: AppointmentService, protected authService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
    this.getAllAvailableAppointments(this.defPageEvent);
  }

  loadAppointments(event?: PageEvent): void {

  }

  getAllAvailableAppointments(pageEvent: PageEvent) {
    this.appointmentService.getAppointmentsForUsersWithDoctorIdFromBackend(this.doctorId, pageEvent.pageIndex, pageEvent.pageSize)
      .subscribe({
        next: (data) => {
          console.log(data)
          this.appointments=data.content;
          this.totalElements=data.totalElements;
        },
        error: (error) => {
          console.log(error)
        }
      })
  }



registerAppointment(appointmentId: number): void {
  this.appointmentService.registerUserForAppointment(this.authService.getUserId(), appointmentId)
    .subscribe({
      next: (data) => {
        console.log(data)
        this.snackBar.open('Appointment has been booked', undefined, {
          verticalPosition: 'top',
          horizontalPosition: 'start',
          duration: 5000
        })


        if (this.authService.isSimpleUser()) {
          this.router.navigate(['/user', this.authService.getUserId()]);
        }
        if (this.authService.isDoctor()) {
          this.router.navigate(['/doctor', this.authService.getUserId()]);
        }

      },
      error: (error) => {
        console.log(error)
        this.notification = error.error.message
        setTimeout(() => {
          this.renderer.addClass(this.child.nativeElement, 'hidden');
          setTimeout(() => {
            this.notification = null;
          }, 1000)
        }, 3000)
      }
    })
}

}
