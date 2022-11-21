import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableDoctorAppointmentsComponent } from './available-doctor-appointments.component';

describe('AvailableDoctorAppointmentsComponent', () => {
  let component: AvailableDoctorAppointmentsComponent;
  let fixture: ComponentFixture<AvailableDoctorAppointmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvailableDoctorAppointmentsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AvailableDoctorAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
