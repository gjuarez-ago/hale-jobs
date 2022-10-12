import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewJobsFComponent } from './view-jobs-f.component';

describe('ViewJobsFComponent', () => {
  let component: ViewJobsFComponent;
  let fixture: ComponentFixture<ViewJobsFComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewJobsFComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewJobsFComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
