export class JobType {
  id: number | null;
  name: string | null;

  constructor(id: number = 0, name: string = '') {
    this.id = id;
    this.name = name;
  }
}
