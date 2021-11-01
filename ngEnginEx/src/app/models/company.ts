import { User } from './user';

export class Company {
  id: number;
  companyName: string;
  users: User[];

  constructor(
    id: number = 0,
    companyName: string = '',
    users: User[] = []
  ) {
    this.id = id;
    this.companyName = companyName;
    this.users = users;
  }
}
