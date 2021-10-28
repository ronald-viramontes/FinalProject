import { CompileNgModuleMetadata } from '@angular/compiler';
import { Company } from './company';
import { User } from './user';

export class Client {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  imageUrl: string;
  user: User;

  constructor(
    id: number = 0,
    firstName: string = '',
    lastName: string = '',
    email: string = '',
    phoneNumber: string,
    imageUrl: string = '',
    user: User = new User()
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.imageUrl = imageUrl;
    this.user = user;
  }
}
