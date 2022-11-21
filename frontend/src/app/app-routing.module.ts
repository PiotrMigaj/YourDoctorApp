import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppointmentFormComponent } from './components/appointment-form/appointment-form.component';
import { DoctorDetailsComponent } from './components/doctor-details/doctor-details.component';
import { AllDoctorsListComponent } from './components/doctor-list/all-doctors-list/all-doctors-list.component';
import { DoctorRegistrationFormComponent } from './components/doctor-registration-form/doctor-registration-form.component';
import { HomeComponent } from './components/home/home.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { MakeAppointmentDetailsComponent } from './components/make-appointment-details/make-appointment-details.component';
import { TestComponent } from './components/test/test.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { UserRegistrationFormComponent } from './components/user-registration-form/user-registration-form.component';
import { AdminRoleGuard } from './services/authentication-service/admin-role.guard';
import { AuthenticationGuard } from './services/authentication-service/authentication.guard';
import { UserRoleGuard } from './services/authentication-service/user-role.guard';

const routes: Routes = [
  { path: "", redirectTo: "home", pathMatch: "full" },
  { path: "home", component: HomeComponent },
  { path: "test", component: TestComponent },
  { path: "login", component: LoginFormComponent },
  { path: "doctors", component: AllDoctorsListComponent },
  { path: "doctor/:id", component: DoctorDetailsComponent, canActivate: [AuthenticationGuard, AdminRoleGuard]},
  { path: "doctor/:id/address/:addressId/appointment-form", component: AppointmentFormComponent,canActivate: [AuthenticationGuard, AdminRoleGuard] },
  { path: "doctor/:id/make-appointment", component: MakeAppointmentDetailsComponent },
  { path: "user-register", component: UserRegistrationFormComponent },
  { path: "doctor-register", component: DoctorRegistrationFormComponent },
  { path: "user/:id", component: UserDetailsComponent,canActivate: [AuthenticationGuard, UserRoleGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
