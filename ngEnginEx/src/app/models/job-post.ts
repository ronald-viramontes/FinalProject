import { Client } from "./client";
import { JobStatus } from "./job-status";
import { JobType } from "./job-type";

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
  client: Client;
  status: JobStatus;

  constructor(id: number = 0, jobRequirements: string = '', startDate: string = '', completionDate: string = '',
    developersNeeded: number = 0, jobActive: boolean = true, datePosted: string = '', dateClosed: string = '',
    type: JobType = new JobType(), client: Client = new Client(), status: JobStatus = new JobStatus()) {
      this.id = id;
      this.jobRequirements = jobRequirements;
      this.startDate = startDate;
      this.completionDate = completionDate;
      this.developersNeeded = developersNeeded;
      this.jobActive = jobActive;
      this.datePosted = datePosted;
      this.dateClosed = dateClosed;
      this.type = type;
      this.client = client;
      this.status = status;
    }
}
