import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeAppointmentDetailsComponent } from './make-appointment-details.component';

describe('MakeAppointmentDetailsComponent', () => {
  let component: MakeAppointmentDetailsComponent;
  let fixture: ComponentFixture<MakeAppointmentDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakeAppointmentDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MakeAppointmentDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
