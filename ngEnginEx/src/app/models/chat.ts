import { User } from './user';

export class Chat {
  id: number;
  subject: string;
  message: string;
  dateTimeStamp: string;
  sender: User;
  receiver: User;
  constructor(
    id: number = 0,
    subject: string = '',
    message: string = '',
    dateTimeStamp: string = '',
    sender: User = new User(),
    receiver: User = new User()
  ) {
    this.id = id;
    this.subject = subject;
    this.message = message;
    this.dateTimeStamp = dateTimeStamp;
    this.sender = sender;
    this.receiver = receiver;
  }
}
