import { AppStatus } from './app-status';
import { JobApplicationComment } from './job-application-comment';
import { JobDetail } from './job-detail';
import { JobPost } from './job-post';
import { User } from './user';

export class JobApplication {
  id: number;
  appStatus: AppStatus;
  date: string;
  jobPost: JobPost;
  user: User;
  decisionDate: string;
  detail: JobDetail | null;
  comments: JobApplicationComment[] = [];

  constructor(
    id: number = 0,
    appStatus = new AppStatus(),
    date: string = '',
    jobPost: JobPost = new JobPost(),
    user: User = new User(),
    decisionDate: string = '',
    detail: JobDetail | null = null,
    comments: JobApplicationComment[] = []
  ) {
    this.id = id;
    this.appStatus = appStatus;
    this.date = date;
    this.jobPost = jobPost;
    this.user = user;
    this.decisionDate = decisionDate;
    this.detail = detail;
    this.comments = comments;
  }
}
