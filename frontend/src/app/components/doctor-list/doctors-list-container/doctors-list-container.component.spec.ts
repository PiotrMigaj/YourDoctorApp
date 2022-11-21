import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorsListContainerComponent } from './doctors-list-container.component';

describe('DoctorsListContainerComponent', () => {
  let component: DoctorsListContainerComponent;
  let fixture: ComponentFixture<DoctorsListContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoctorsListContainerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctorsListContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
