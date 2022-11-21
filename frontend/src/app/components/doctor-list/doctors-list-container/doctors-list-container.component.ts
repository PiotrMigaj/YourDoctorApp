import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { DoctorDto } from 'src/app/models/doctor';
import { AuthenticationService } from 'src/app/services/authentication-service/authentication.service';

@Component({
  selector: 'app-doctors-list-container',
  templateUrl: './doctors-mat-card-list.component.html',
  styleUrls: ['./doctors-list-container.component.css']
})
export class DoctorsListContainerComponent implements OnInit, OnChanges {

  displayedColumns: string[] = [
    'identifier',
    'firstName',
    'lastName',
    'email',
    'phoneNumber',
    'appointment-button'
  ]

  @Input() doctors: DoctorDto[] =[];
  @Input() totalElements: number=0;
  @Input() loadingList:boolean = false;

  @Output() loadDoctorsEvent = new EventEmitter<PageEvent>();

  constructor(private router: Router, protected authService:AuthenticationService) { }

  ngOnInit(): void {
    this.loadDoctorsEvent.emit({
      pageSize: 5,
      pageIndex: 0,
      length: 0
    });
  }

  ngOnChanges():void{
    console.log(this.doctors)
  }

  loadDoctors(event?: PageEvent):void{
    this.loadDoctorsEvent.emit(event)
  }

}
