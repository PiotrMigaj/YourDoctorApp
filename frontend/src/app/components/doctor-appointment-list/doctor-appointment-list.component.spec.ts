import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorAppointmentListComponent } from './doctor-appointment-list.component';

describe('DoctorAppointmentListComponent', () => {
  let component: DoctorAppointmentListComponent;
  let fixture: ComponentFixture<DoctorAppointmentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoctorAppointmentListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctorAppointmentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
