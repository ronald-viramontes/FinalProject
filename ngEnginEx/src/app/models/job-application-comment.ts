import { JobApplication } from './job-application';

export class JobApplicationComment {
  id: number;
  comment: string;
  commentDate: string;
  jobApplication: JobApplication;
  baseComment: JobApplicationComment;

  constructor(
    id: number = 0,
    comment: string = '',
    commentDate: string = '',
    jobApplication: JobApplication = new JobApplication(),
    baseComment: JobApplicationComment = new JobApplicationComment()
  ) {
    this.id = id;
    this.comment = comment;
    this.commentDate = commentDate;
    this.jobApplication = jobApplication;
    this.baseComment = baseComment;
  }
}
