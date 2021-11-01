import { Company } from './company';
import { Education } from './education';
import { Experience } from './experience';
import { JobApplication } from './job-application';
import { JobApplicationComment } from './job-application-comment';
import { JobDetail } from './job-detail';
import { JobPost } from './job-post';
import { JobStatus } from './job-status';
import { Skill } from './skill';

export class User {
  id: number;
  username: string;
  password: string;
  enabled: boolean;
  role: string;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  imageUrl: string;
  company: Company;
  educations: Education[];
  experiences: Experience[];
  applications: JobApplication[];
  // applicationComments: JobApplicationComment[];
  // jobDetails: JobDetail[];
  posts: JobPost[];
  jobStatuses: JobStatus[];
  skills: Skill[];

  constructor(
    id: number = 0,
    username: string = '',
    password: string = '',
    enabled: boolean = true,
    role: string = '',
    firstName: string = '',
    lastName: string = '',
    email: string = '',
    phoneNumber: string = '',
    imageUrl: string = '',
    company: Company = new Company(),
    educations: Education[] = [],
    experiences: Experience[] = [],
    applications: JobApplication[] = [],
    // applicationComments = [],
    // jobDetails = [],
    posts: JobPost[] = [],
    jobStatuses: JobStatus[] = [],
    skills: Skill[] = []


  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.imageUrl = imageUrl;
    this.company = company;
    this.educations = educations;
    this.experiences = experiences;
    this.applications = applications;
    // this.applicationComments = applicationComments;
    // this.jobDetails = jobDetails;
    this.posts = posts;
    this.jobStatuses = jobStatuses;
    this.skills = skills;
  }
}
