import { JobApplication } from './job-application';

export class JobApplicationComment {
  id: number;
  comment: string;
  date: string;
  jobApplication: JobApplication;
  replies: JobApplicationComment[];

  constructor(
    id: number = 0,
    comment: string = '',
    date: string = '',
    jobApplication: JobApplication = new JobApplication(),
    replies: JobApplicationComment[] = []
  ) {
    this.id = id;
    this.comment = comment;
    this.date = date;
    this.jobApplication = jobApplication;
    this.replies = replies;
  }
}
