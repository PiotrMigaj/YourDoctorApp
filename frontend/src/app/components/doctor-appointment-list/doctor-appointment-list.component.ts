import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { AppointmentForUserDto } from 'src/app/models/appointment';
import { AppointmentService } from 'src/app/services/appointment-service/appointment.service';

@Component({
  selector: 'app-doctor-appointment-list',
  templateUrl: './doctor-appointment-list.component.html',
  styleUrls: ['./doctor-appointment-list.component.css']
})
export class DoctorAppointmentListComponent implements OnInit, OnChanges {

  @Input() userId!: number | null;
  totalElements!: number;

  pageEvent: PageEvent = {
    pageSize: 5,
    pageIndex: 0,
    length: 0
  }

  appointments: AppointmentForUserDto[] = []

  constructor(private appointmentService: AppointmentService) { }

  ngOnInit(): void {
    this.getAppointments(this.userId, this.pageEvent);
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(`userId: ${this.userId}`);
  }

  getAppointments(userId: number | null, pageEvent?: PageEvent): void {
    this.appointmentService.getAppointmentsForUserFromBackend(userId, pageEvent?.pageIndex, pageEvent?.pageSize)
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

  loadAppointments($event?: PageEvent): void {
    this.getAppointments(this.userId, $event);
  }

}
