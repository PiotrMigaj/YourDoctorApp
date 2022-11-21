import { Component, ElementRef, OnChanges, OnInit, Renderer2, ViewChild } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CreateAppointmentRequest } from 'src/app/models/appointment';
import { AppointmentService } from 'src/app/services/appointment-service/appointment.service';

@Component({
  selector: 'app-appointment-form',
  templateUrl: './appointment-form.component.html',
  styleUrls: ['./appointment-form.component.css']
})
export class AppointmentFormComponent implements OnInit, OnChanges {

  @ViewChild('ref') child: ElementRef | any;

  createAppointmentRequest: CreateAppointmentRequest;
  sendingUser: boolean = false;
  notification: string | any = null;
  doctorId?:number;
  addressId?:number;
  inputDateOfAppointment!:Date;



  constructor(private snackBar: MatSnackBar,private router: Router,private renderer: Renderer2,private appointmentService:AppointmentService,private route: ActivatedRoute) {
    this.createAppointmentRequest = this.appointmentService.getDefautCreateAppointmentRequest();
  }

  ngOnInit(): void {
    this.doctorId = +this.route.snapshot.paramMap.get('id')!;
    this.addressId = +this.route.snapshot.paramMap.get('addressId')!;
    console.log(`doctorId: ${this.doctorId}, addressId: ${this.addressId}`)
    this.createAppointmentRequest.doctorId = this.doctorId;
    this.createAppointmentRequest.addressId = this.addressId;
  }

  ngOnChanges(): void {
  }


  addDays(date:Date, days:number):Date {
    var result = new Date(date);
    result.setDate(result.getDate() + days);
    return result;
  }


  registerAppointment(): void {

    this.createAppointmentRequest.dateOfAppointment = this.addDays(this.inputDateOfAppointment,1).toISOString().slice(0,10);

    console.log(this.createAppointmentRequest.dateOfAppointment);
    console.log(this.createAppointmentRequest.timeOfAppointment);

    this.sendingUser = true;
    this.appointmentService.registerAppointmentInBackend(this.createAppointmentRequest)
      .subscribe({
        next: (data) => {
          this.sendingUser = false;
          this.snackBar.open('Appointment has been registered', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 5000
          })
          this.router.navigate(['doctor',this.doctorId])
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
    this.createAppointmentRequest = this.appointmentService.getDefautCreateAppointmentRequest();
  }

}
