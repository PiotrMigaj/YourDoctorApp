import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BACKEND_BASE_URL } from 'src/app/models/const';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  constructor(private httpClient:HttpClient,private snackBar:MatSnackBar) { }

  ngOnInit(): void {
  }

  call(type: string) {
    this.httpClient.get(BACKEND_BASE_URL + "test/" + type)
      .subscribe({
        next: (data) => {
          this.snackBar.open(`${type} request succeeded`, undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'end',
            duration: 1500
          })
        },
        error: (error) => {
          this.snackBar.open(`${type} request FAILED`, undefined, {
            verticalPosition: 'bottom',
            horizontalPosition: 'end',
            duration: 1500
          })
        }
      })
  }

  callPublic() {
    console.log("Call Public")
    this.call("public");
  }

  callAnyone() {
    console.log("Call Anyone")
    this.call("anyone");
  }

  callDoctor() {
    console.log("Call Doctor")
    this.call("doctor");
  }
  callAdmin() {
    console.log("Call Admin")
    this.call("admin");
  }
  callSecured() {
    console.log("Call Secured")
    this.call("secured");
  }

}
