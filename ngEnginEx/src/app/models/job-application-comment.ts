import { JobApplication } from "./job-application";

export class JobApplicationComment {

  id: number;
  comment: string;
  commentDate: string;
  jobApplication: JobApplication;
  baseComment: JobApplicationComment;

  constructor(id: number, comment: string, commentDate: string, jobApplication: JobApplication, baseComment: JobApplicationComment){
    this.id = id;
    this.comment = comment;
    this.commentDate = commentDate;
    this.jobApplication = jobApplication;
    this.baseComment = baseComment;
  }
}
