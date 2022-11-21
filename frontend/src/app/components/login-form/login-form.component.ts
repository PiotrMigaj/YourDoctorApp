import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthenticationRequest } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication-service/authentication.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  @ViewChild('ref') child: ElementRef | any;
  notification: string | any = null;
  

  authenticationRequest?:AuthenticationRequest;

  constructor(public authService: AuthenticationService,private router:Router,private renderer: Renderer2,private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.authenticationRequest = this.createEmptyAuthenticationRequest();
    
  }

  login():void{
    this.authService.authenticate(this.authenticationRequest!)
  }

  clearForm():void{
    this.authenticationRequest=this.createEmptyAuthenticationRequest();
  }

  createEmptyAuthenticationRequest():AuthenticationRequest{
    return {
      login: '',
      pass: ''
    }
  }

}
