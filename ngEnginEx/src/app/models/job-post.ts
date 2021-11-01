import { JobApplication } from "./job-application";
import { JobStatus } from "./job-status";
import { JobType } from "./job-type";
import { User } from "./user";

export class JobPost {

  id: number;
  jobRequirements: string;
  startDate: string;
  completionDate: string;
  developersNeeded: number;
  jobActive: boolean;
  datePosted: string;
  dateClosed: string;
  type: JobType;
  user: User;
  status: JobStatus;
  applications: JobApplication[];

  constructor(id: number = 0, jobRequirements: string = '', startDate: string = '', completionDate: string = '',
    developersNeeded: number = 0, jobActive: boolean = true, datePosted: string = '', dateClosed: string = '',
    type: JobType = new JobType(), user: User = new User(), status: JobStatus = new JobStatus(), applications: JobApplication[] = []) {
      this.id = id;
      this.jobRequirements = jobRequirements;
      this.startDate = startDate;
      this.completionDate = completionDate;
      this.developersNeeded = developersNeeded;
      this.jobActive = jobActive;
      this.datePosted = datePosted;
      this.dateClosed = dateClosed;
      this.type = type;
      this.user = user;
      this.status = status;
      this.applications = applications;
    }
}
