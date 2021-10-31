import { CompileNgModuleMetadata } from '@angular/compiler';
import { Company } from './company';
import { JobPost } from './job-post';
import { User } from './user';

export class Client {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  imageUrl: string;
  jobPosts: JobPost[];
  // user: User;

  constructor(
    id: number = 0,
    firstName: string = '',
    lastName: string = '',
    email: string = '',
    phoneNumber: string = '',
    imageUrl: string = '',
    jobPosts: JobPost[] = []
    // user: User = new User()
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.imageUrl = imageUrl;
    this.jobPosts = jobPosts;
    // this.user = user;
  }
}
