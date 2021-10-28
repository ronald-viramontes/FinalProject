import { Developer } from "./developer";

export class Skill {

  id: number;
  skillTitle: string;
  skillLevel: string;
  developer: Developer;

  constructor(id: number, skillTitle: string, skillLevel: string, developer: Developer){
    this.id = id;
    this.skillTitle = skillTitle;
    this.skillLevel = skillLevel;
    this.developer = developer;
  }
}
