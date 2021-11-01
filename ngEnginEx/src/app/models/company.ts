import { User } from './user';

export class Company {
  id: number;
  companyName: string;
  user: User;

  constructor(
    id: number = 0,
    companyName: string = '',
    user: User = new User()
  ) {
    this.id = id;
    this.companyName = companyName;
    this.user = user;
  }
}
