import { Component } from '@angular/core';
import { AuthenticationService } from './services/authentication-service/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'yourDoctorFrontend';

  constructor(public authService:AuthenticationService){

  }
}
