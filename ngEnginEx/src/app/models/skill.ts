import { User } from './user';

export class Skill {
  id: number;
  skillTitle: string;
  skillLevel: string;
  user: User;

  constructor(
    id: number = 0,
    skillTitle: string = '',
    skillLevel: string = '',
    user: User = new User()
  ) {
    this.id = id;
    this.skillTitle = skillTitle;
    this.skillLevel = skillLevel;
    this.user = user;
  }
}
