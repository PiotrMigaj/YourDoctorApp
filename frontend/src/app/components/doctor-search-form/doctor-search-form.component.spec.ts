import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorSearchFormComponent } from './doctor-search-form.component';

describe('DoctorSearchFormComponent', () => {
  let component: DoctorSearchFormComponent;
  let fixture: ComponentFixture<DoctorSearchFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoctorSearchFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctorSearchFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
