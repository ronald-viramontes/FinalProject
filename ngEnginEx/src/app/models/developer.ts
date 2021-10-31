import { Education } from "./education";
import { Experience } from "./experience";
import { JobApplication } from "./job-application";
import { Skill } from "./skill";
import { User } from "./user";

export class Developer {

  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  imageUrl: string;
  // user: User;
  educations: Education[];
  skills: Skill[];
  experiences: Experience[];
  applications: JobApplication[];

  constructor(id: number =0, firstName: string ='', lastName: string ='', email: string ='',
  phoneNumber: string ='', imageUrl: string ='', educations: Education[] = [], skills: Skill[] = [],
  experiences: Experience[] =[], applications: JobApplication[] =[]
  // user: User = new User()
  ){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.imageUrl = imageUrl;
    // this.user = user;
    this.educations = educations;
    this.skills = skills;
    this.experiences = experiences;
    this.applications = applications;
  }
}
