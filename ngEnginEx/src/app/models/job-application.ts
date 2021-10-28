import { Developer } from "./developer";
import { JobPost } from "./job-post";

export class JobApplication {

  id: number;
  applicationApproval: boolean;
  applicationStatus: string;
  applicationDate: string;
  jobPost: JobPost;
  developer: Developer;
  decisionDate: string;

  constructor(  id: number, applicationApproval: boolean, applicationStatus: string,
    applicationDate: string, jobPost: JobPost, developer: Developer, decisionDate: string){
      this.id = id;
      this.applicationApproval = applicationApproval;
      this.applicationStatus = applicationStatus;
      this.applicationDate = applicationDate;
      this.jobPost = jobPost;
      this.developer = developer;
      this.decisionDate = decisionDate;
  }
}
