import { User } from "./user";

export class Developer {

  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  imageUrl: string;
  user: User;

  constructor(id: number, firstName: string, lastName: string, email: string, phoneNumber: string, imageUrl: string, user: User){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.imageUrl = imageUrl;
    this.user = user;
  }
}
