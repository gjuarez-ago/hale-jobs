import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewJobsIComponent } from './view-jobs-i.component';

describe('ViewJobsIComponent', () => {
  let component: ViewJobsIComponent;
  let fixture: ComponentFixture<ViewJobsIComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewJobsIComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewJobsIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
