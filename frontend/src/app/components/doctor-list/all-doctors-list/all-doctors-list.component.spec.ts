import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllDoctorsListComponent } from './all-doctors-list.component';

describe('AllDoctorsListComponent', () => {
  let component: AllDoctorsListComponent;
  let fixture: ComponentFixture<AllDoctorsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllDoctorsListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllDoctorsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
