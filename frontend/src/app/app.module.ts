import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './components/home/home.component';
import { TestComponent } from './components/test/test.component';
import { AuthenticationService } from './services/authentication-service/authentication.service';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDividerModule } from '@angular/material/divider';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import {MatPaginatorModule} from '@angular/material/paginator';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { AuthenticationInterceptor } from './services/authentication-service/authentication.interceptor';
import { DoctorSearchFormComponent } from './components/doctor-search-form/doctor-search-form.component';
import { AllDoctorsListComponent } from './components/doctor-list/all-doctors-list/all-doctors-list.component';
import { DoctorsListContainerComponent } from './components/doctor-list/doctors-list-container/doctors-list-container.component';
import {MatMenuModule} from '@angular/material/menu';
import { UserRegistrationFormComponent } from './components/user-registration-form/user-registration-form.component';
import { DoctorRegistrationFormComponent } from './components/doctor-registration-form/doctor-registration-form.component';
import { DoctorService } from './services/doctor-service/doctor.service';
import { AppUserService } from './services/appuser-service/app-user.service';
import { DoctorDetailsComponent } from './components/doctor-details/doctor-details.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { AppointmentService } from './services/appointment-service/appointment.service';
import { AllAppointmentListComponent } from './components/appointment-list/all-appointment-list/all-appointment-list.component';
import { AppointmentListContainerComponent } from './components/appointment-list/appointment-list-container/appointment-list-container.component';
import { AppointmentFormComponent } from './components/appointment-form/appointment-form.component';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { MakeAppointmentDetailsComponent } from './components/make-appointment-details/make-appointment-details.component';
import { DoctorAppointmentListComponent } from './components/doctor-appointment-list/doctor-appointment-list.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { AvailableDoctorAppointmentsComponent } from './components/appointment-list/available-doctor-appointments/available-doctor-appointments.component';
import {MatTabsModule} from '@angular/material/tabs';




@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TestComponent,
    LoginFormComponent,
    DoctorSearchFormComponent,
    AllDoctorsListComponent,
    DoctorsListContainerComponent,
    UserRegistrationFormComponent,
    DoctorRegistrationFormComponent,
    DoctorDetailsComponent,
    UserDetailsComponent,
    AllAppointmentListComponent,
    AppointmentListContainerComponent,
    AppointmentFormComponent,
    MakeAppointmentDetailsComponent,
    DoctorAppointmentListComponent,
    AvailableDoctorAppointmentsComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatCardModule,
    MatGridListModule,
    MatFormFieldModule,
    MatIconModule,
    MatTableModule,
    MatCheckboxModule,
    MatRadioModule,
    MatSelectModule,
    MatButtonModule,
    MatNativeDateModule,
    MatInputModule,
    MatDatepickerModule,
    MatDividerModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatProgressBarModule,
    MatSnackBarModule,
    MatPaginatorModule,
    MatMenuModule,
    NgxMaterialTimepickerModule,
    MatExpansionModule,
    MatTabsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptor,
      multi: true
    },
    AuthenticationService,
    AppUserService,
    DoctorService,
    AppointmentService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
