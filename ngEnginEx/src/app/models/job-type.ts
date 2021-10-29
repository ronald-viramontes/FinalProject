export class JobType {

  id: number | null;
  name: string | null;
  description: string | null;

  constructor(id: number = 0, name: string = '', description: string = ''){
    this.id = id;
    this.name = name;
    this.description = description;
  }
}
