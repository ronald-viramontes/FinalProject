import { User } from './user';

export class Experience {
  id: number;
  jobTitle: string;
  companyName: string;
  startDate: string;
  endDate: string;
  user: User;

  constructor(
    id: number = 0,
    jobTitle: string = '',
    companyName: string = '',
    startDate: string = '',
    endDate: string = '',
    user: User = new User()
  ) {
    this.id = id;
    this.jobTitle = jobTitle;
    this.companyName = companyName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.user = user;
  }
}
