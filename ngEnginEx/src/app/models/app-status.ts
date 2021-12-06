import { JobApplication } from './job-application';

export class AppStatus {
  id: number;
  name: string;
  applications: JobApplication[];
  constructor(
    id: number = 0,
    name: string = '',
    applications: JobApplication[] = []
  ) {
    this.id = id;
    this.name = name;
    this.applications = applications;
  }
}
