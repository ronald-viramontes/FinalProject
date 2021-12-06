import { User } from './user';

export class Education {
  id: number;
  educationType: string;
  institutionName: string;
  degreeCertificateName: string;
  completeDate: string;
  user!: User;

  constructor(
    id: number = 0,
    educationType: string = '',
    institutionName: string = '',
    degreeCertificateName: string = '',
    completeDate: string = '',
    user: User = new User()
  ) {
    this.id = id;
    this.educationType = educationType;
    this.institutionName = institutionName;
    this.degreeCertificateName = degreeCertificateName;
    this.completeDate = completeDate;
    this.user = user;
  }
}
