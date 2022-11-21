import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DoctorDto } from 'src/app/models/doctor';
import { DoctorService } from 'src/app/services/doctor-service/doctor.service';

@Component({
  selector: 'app-make-appointment-details',
  templateUrl: './make-appointment-details.component.html',
  styleUrls: ['./make-appointment-details.component.css']
})
export class MakeAppointmentDetailsComponent implements OnInit {

  public doctorDto!:DoctorDto;
  public doctorId: number = 0;

  public appointmentsForDoctor:boolean = false;

  constructor(private doctorService:DoctorService,private route: ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    this.doctorId = +this.route.snapshot.paramMap.get('id')!;
    this.getDoctor(this.doctorId);
  }

  getDoctor(doctorId:number):void{
    this.doctorService.getDoctorFromBackendById(doctorId)
    .subscribe({
      next: (data) => {
        console.log(data)
        this.doctorDto = data;
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

}
