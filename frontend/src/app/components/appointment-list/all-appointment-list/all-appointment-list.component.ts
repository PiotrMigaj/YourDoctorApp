import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { AppointmentForDoctorDto } from 'src/app/models/appointment';
import { PageResponse } from 'src/app/models/pagination';
import { AppointmentService } from 'src/app/services/appointment-service/appointment.service';
import { AuthenticationService } from 'src/app/services/authentication-service/authentication.service';

@Component({
  selector: 'app-all-appointment-list',
  templateUrl: './all-appointment-list.component.html',
  styleUrls: ['./all-appointment-list.component.css']
})
export class AllAppointmentListComponent implements OnInit,OnChanges {

  @Input() doctorId!:number|null;

  pageResponse: PageResponse<AppointmentForDoctorDto>={
    content: [],
    totalElements: 0
  };;
  loadingList: boolean = false;
  pageEvent?:PageEvent;

  defPageEvent:PageEvent={
    pageSize: 5,
    pageIndex: 0,
    length: 0
  }

  constructor(private appointmentService:AppointmentService,private autService:AuthenticationService) { }

  ngOnInit(): void {
    console.log(`doctorId from appointments list: ${this.doctorId}`)

  }

  ngOnChanges():void{
    console.log(`doctorId from appointments list: ${this.doctorId}`)
    this.getAppointmentsForDoctor(this.defPageEvent);
  }

  getAppointments(pageEvent?:PageEvent): void {
    this.pageEvent=pageEvent;
    this.loadingList = true;
    this.getAppointmentsForDoctor(pageEvent);
  }

  private getAppointmentsForDoctor(pageEvent?:PageEvent):void{
    this.appointmentService.getAppointmentsForDoctorFromBackend(this.doctorId,pageEvent?.pageIndex, pageEvent?.pageSize)
    .subscribe({
      next: (data) => {
        this.loadingList = false
        console.log(data)
        this.pageResponse = data;
      },
      error: (error) => {
        this.loadingList = false
        console.log(error)
      }
    })
  }

  

}
