import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ActivatedRoute } from '@angular/router';
import { DoctorDto } from 'src/app/models/doctor';
import { PageResponse } from 'src/app/models/pagination';
import { DoctorService } from 'src/app/services/doctor-service/doctor.service';

@Component({
  selector: 'app-all-doctors-list',
  templateUrl: './all-doctors-list.component.html',
  styleUrls: ['./all-doctors-list.component.css']
})
export class AllDoctorsListComponent implements OnInit {

  private city?: string
  private specialization?: string
  pageResponse: PageResponse<DoctorDto>={
    content: [],
    totalElements: 0
  };;
  loadingList: boolean = false;
  pageEvent?:PageEvent;

  constructor(private route: ActivatedRoute, private doctorService: DoctorService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.city = params['city'];
      this.specialization = params['specialization'];
    })
    console.log(this.city)
    console.log(this.specialization)
    // this.getDoctors();
  }

  getDoctors(pageEvent?:PageEvent): void {
    this.pageEvent=pageEvent;
    this.loadingList = true;
    this.doctorService.getDoctorsFromBackend(this.city,this.specialization,pageEvent?.pageIndex, pageEvent?.pageSize)
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
