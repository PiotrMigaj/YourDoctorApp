import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllAppointmentListComponent } from './all-appointment-list.component';

describe('AllAppointmentListComponent', () => {
  let component: AllAppointmentListComponent;
  let fixture: ComponentFixture<AllAppointmentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllAppointmentListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllAppointmentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
