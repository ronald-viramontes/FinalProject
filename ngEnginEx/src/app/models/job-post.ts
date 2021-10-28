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
  jobType: JobType;
  client: Client;
  jobStatus: JobStatus;

  constructor(  id: number, jobRequirements: string, startDate: string, completionDate: string,
    developersNeeded: number, jobActive: boolean, datePosted: string, dateClosed: string,
    jobType: JobType, client: Client, jobStatus: JobStatus) {
      this.id = id;
      this.jobRequirements = jobRequirements;
      this.startDate = startDate;
      this.completionDate = completionDate;
      this.developersNeeded = developersNeeded;
      this.jobActive = jobActive;
      this.datePosted = datePosted;
      this.dateClosed = dateClosed;
      this.jobType = jobType;
      this.client = client;
      this.jobStatus = jobStatus;
    }
}
