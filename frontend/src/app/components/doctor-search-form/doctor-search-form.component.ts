import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DoctorDto } from 'src/app/models/doctor';
import { AuthenticationService } from 'src/app/services/authentication-service/authentication.service';
import { DoctorService } from 'src/app/services/doctor-service/doctor.service';

@Component({
  selector: 'app-doctor-search-form',
  templateUrl: './doctor-search-form.component.html',
  styleUrls: ['./doctor-search-form.component.css']
})
export class DoctorSearchFormComponent implements OnInit {

  city:string|null = null;
  specialization:string|null = null;
  specializations:string[]=[]
  doctors:DoctorDto[]=[];

  constructor(private router: Router,private doctorService:DoctorService, protected authService:AuthenticationService) { }

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
    this.doctorService.getRecentlyJoinedDoctors()
    .subscribe({
      next: (data) => {
        this.doctors = data
        console.log(data)
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

  submitForm():void{
    this.router.navigate(
      ['/doctors'],
      { queryParams: { city: this.city,specialization:this.specialization } }
    );
  }

  clearForm():void{
    this.city=null;
    this.specialization=null;
  }



}
