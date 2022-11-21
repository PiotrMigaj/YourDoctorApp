import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { AppointmentForUserDto } from 'src/app/models/appointment';
import { AppUserDto } from 'src/app/models/user';
import { AppointmentService } from 'src/app/services/appointment-service/appointment.service';
import { AppUserService } from 'src/app/services/appuser-service/app-user.service';



@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  totalElements!: number;
  appUserDto!:AppUserDto;
  notification: string | any = null;
  appointments:AppointmentForUserDto[]=[]

  pageEvent:PageEvent={
      pageSize: 5,
      pageIndex: 0,
      length: 0
  }

  constructor(private renderer: Renderer2,private appUserService:AppUserService, private route:ActivatedRoute,private snackBar: MatSnackBar,private appointmentService:AppointmentService) { }

  ngOnInit(): void {
    let userId:number = +this.route.snapshot.paramMap.get('id')!;
    this.getUser(userId);
    this.getAppointments(userId,this.pageEvent);
  }

  getUser(userId:number):void{
    this.appUserService.getAppUserByIdFromBackend(userId)
    .subscribe({
      next: (data) => {
        console.log(data)
        this.appUserDto = data;
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

  getAppointments(userId:number,pageEvent?:PageEvent):void{
    this.appointmentService.getAppointmentsForUserFromBackend(userId,pageEvent?.pageIndex,pageEvent?.pageSize)
    .subscribe({
      next: (data) => {
        console.log(data.content)
        this.appointments = data.content;
        this.totalElements = data.totalElements;
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

  loadAppointments($event?: PageEvent):void{
    this.getAppointments(this.appUserDto.id,$event);
  }
}
