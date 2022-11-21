import { Component, ElementRef, EventEmitter, Input, OnChanges, OnInit, Output, Renderer2, ViewChild } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AppointmentDto, AppointmentForDoctorDto } from 'src/app/models/appointment';
import { AppointmentService } from 'src/app/services/appointment-service/appointment.service';
import { AuthenticationService } from 'src/app/services/authentication-service/authentication.service';

@Component({
  selector: 'app-appointment-list-container',
  templateUrl: './appointment-list-container.component.html',
  styleUrls: ['./appointment-list-container.component.css']
})
export class AppointmentListContainerComponent implements OnInit, OnChanges {

  @ViewChild('ref') child: ElementRef | any;
  notification: string | any = null;

  @Input() appointments: AppointmentForDoctorDto[] =[];
  @Input() totalElements: number=0;
  @Input() loadingList:boolean = false;


  @Output() loadAppointmentsEvent = new EventEmitter<PageEvent>();

  constructor(private snackBar: MatSnackBar,private renderer: Renderer2,private appointmentService:AppointmentService,private authService:AuthenticationService,private router:Router) { }

  ngOnInit(): void {
    this.loadAppointmentsEvent.emit({
      pageSize: 5,
      pageIndex: 0,
      length: 0
    });
  }

  ngOnChanges():void{
    console.log(this.appointments)
  }

  loadAppointments(event?: PageEvent):void{
    this.loadAppointmentsEvent.emit(event)
  }

  

}
