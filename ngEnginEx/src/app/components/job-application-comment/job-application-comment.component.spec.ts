import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobApplicationCommentComponent } from './job-application-comment.component';

describe('JobApplicationCommentComponent', () => {
  let component: JobApplicationCommentComponent;
  let fixture: ComponentFixture<JobApplicationCommentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JobApplicationCommentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JobApplicationCommentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
