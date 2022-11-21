import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentListContainerComponent } from './appointment-list-container.component';

describe('AppointmentListContainerComponent', () => {
  let component: AppointmentListContainerComponent;
  let fixture: ComponentFixture<AppointmentListContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentListContainerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentListContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
