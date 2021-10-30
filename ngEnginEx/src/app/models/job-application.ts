import { Developer } from "./developer";
import { JobPost } from "./job-post";

export class JobApplication {

  id: number;
  approved: boolean;
  status: string;
  date: string;
  jobPost: JobPost;
  developer: Developer;
  decisionDate: string;

  constructor(  id: number = 0, approved: boolean = false, status: string = '',
    date: string = '', jobPost: JobPost = new JobPost(), developer: Developer = new Developer(), decisionDate: string = ''){
      this.id = id;
      this.approved = approved;
      this.status = status;
      this.date = date;
      this.jobPost = jobPost;
      this.developer = developer;
      this.decisionDate = decisionDate;
  }
}
