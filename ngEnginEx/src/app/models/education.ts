import { Developer } from "./developer";

export class Education {

  id: number;
  educationType: string;
  institutionName: string;
  degreeCertificateName: string;
  completeDate: string;
  developer: Developer;

  constructor(id: number, educationType: string, institutionName: string, degreeCertificateName: string, completeDate: string, developer: Developer){
    this.id = id;
    this.educationType = educationType;
    this.institutionName = institutionName;
    this.degreeCertificateName = degreeCertificateName;
    this.completeDate = completeDate;
    this.developer = developer;
  }
}
