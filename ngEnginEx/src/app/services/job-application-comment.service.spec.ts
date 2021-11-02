import { TestBed } from '@angular/core/testing';

import { JobApplicationCommentService } from './job-application-comment.service';

describe('JobApplicationCommentService', () => {
  let service: JobApplicationCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JobApplicationCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
