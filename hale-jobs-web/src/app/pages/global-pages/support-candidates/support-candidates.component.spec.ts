import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupportCandidatesComponent } from './support-candidates.component';

describe('SupportCandidatesComponent', () => {
  let component: SupportCandidatesComponent;
  let fixture: ComponentFixture<SupportCandidatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SupportCandidatesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SupportCandidatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
