import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { User } from './user';

export class Chat {
  id: number;
  subject: string;
  message: string;
  dateTimeStamp: string;
  // reading: boolean;
  sender: User | null;
  receiver: User | null;
  reply: Chat | null;
  replies: Chat[];
  constructor(
    id: number = 0,
    subject: string = '',
    message: string = '',
    dateTimeStamp: string = '',
    // reading: boolean = false,
    sender: User | null = null,
    receiver: User | null = null,
    reply: Chat | null = null,
    replies: Chat[] = []
  ) {
    this.id = id;
    this.subject = subject;
    this.message = message;
    this.dateTimeStamp = dateTimeStamp;
    // this.reading = reading;
    this.sender = sender;
    this.receiver = receiver;
    this.reply = reply;
    this.replies = replies;
  }
}
