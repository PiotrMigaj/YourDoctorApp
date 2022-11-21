import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DoctorDto } from 'src/app/models/doctor';
import { DoctorService } from 'src/app/services/doctor-service/doctor.service';

@Component({
  selector: 'app-doctor-details',
  templateUrl: './doctor-details.component.html',
  styleUrls: ['./doctor-details.component.css']
})
export class DoctorDetailsComponent implements OnInit {

  public doctorDto!:DoctorDto;

  constructor(private doctorService:DoctorService,private route: ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    let userId:number = +this.route.snapshot.paramMap.get('id')!;
    this.getDoctor(userId);
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

  rediretToAppointmentForm():void{
    let path:string = `/doctor/${this.doctorDto.id}/address/${this.getAddressId()}/appointment-form`;
    console.log(path);
    this.router.navigateByUrl(path)
  }

  getAddressId():number|null{
    return this.doctorDto.addresses[0].id;
  }



}
