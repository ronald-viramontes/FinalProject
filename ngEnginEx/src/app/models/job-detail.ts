import { JobApplication } from './job-application';
import { JobPost } from './job-post';

export class JobDetail {
  id: number;
  startDate: string;
  finishDate: string;
  rating: number;
  comment: string;
  application: JobApplication | null;
  // jobPost: JobPost | null;

  constructor(
    id: number = 0,
    startDate: string = '',
    finishDate: string = '',
    rating: number = 0,
    comment: string = '',
    application: JobApplication | null = null
    // jobPost: JobPost | null = null
  ) {
    this.id = id;
    this.startDate = startDate;
    this.finishDate = finishDate;
    this.rating = rating;
    this.comment = comment;
    this.application = application;
    // this.jobPost = jobPost;
  }
}
