import { JobApplication } from './job-application';

export class JobDetail {
  id: number;
  startDate: string;
  finishDate: string;
  rating: number;
  comment: string;
  jobApplication: JobApplication;

  constructor(
    id: number = 0,
    startDate: string = '',
    finishDate: string = '',
    rating: number = 0,
    comment: string = '',
    jobApplication = new JobApplication()
  ) {
    this.id = id;
    this.startDate = startDate;
    this.finishDate = finishDate;
    this.rating = rating;
    this.comment = comment;
    this.jobApplication = jobApplication;
  }
}
