export class JobDetail {

  id: number;
  startDate: string;
  finishDate: string;
  rating: number;
  comment: string;

  constructor(id: number, startDate: string, finishDate: string, rating: number, comment: string){
    this.id = id;
    this.startDate = startDate;
    this.finishDate = finishDate;
    this.rating = rating;
    this.comment = comment;
  }
}
