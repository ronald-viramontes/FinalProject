import { Developer } from "./developer";

export class Experience {

  id: number;
  jobTitle: string;
  companyName: string;
  startDate: string;
  endDate: string;
  developer: Developer;

  constructor(id: number, jobTitle: string, companyName: string, startDate: string, endDate: string, developer: Developer){
    this.id = id;
    this.jobTitle = jobTitle;
    this.companyName = companyName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.developer = developer;
  }
}
