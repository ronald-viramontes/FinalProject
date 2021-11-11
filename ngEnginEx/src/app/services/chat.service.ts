import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Chat } from '../models/chat';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class ChatService {
  // private baseUrl = 'http://localhost:8091/';
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/chats';
  constructor(private http: HttpClient, private authService: AuthService) {}

  senderIndex(userId: number): Observable<Chat[]> {
    return this.http
      .get<Chat[]>(`${this.url}/users/${userId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Something went wrong getting the sender chat list'
          );
        })
      );
  }
  receiverIndex(userId: number, receiverId: number): Observable<Chat[]> {
    return this.http
      .get<Chat[]>(`${this.url}/${userId}/${receiverId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Something went wrong getting the receiver chat list'
          );
        })
      );
  }

  create(newChat: Chat, userId: number, receiverId: number) {
    console.log(newChat);
    return this.http
      .post<Chat>(
        `${this.url}/users/${userId}/${receiverId}`,
        newChat,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong creating a chat message');
        })
      );
  }
  createReply(newReply: Chat, userId: number, chatId: number) {
    console.log(newReply);
    return this.http
      .post<Chat>(
        `${this.url}/${userId}/chats/${chatId}`,
        newReply,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong creating a chat message');
        })
      );
  }

  update(chatId: number, userId: number, receiverId: number, editChat: Chat) {
    const httpOptions = {};
    return this.http
      .put<Chat>(
        `${this.url}/users/${userId}/receiver/${receiverId}/${chatId}`,
        editChat,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong with update of chat');
        })
      );
  }

  destroy(chatId: number, userId: number) {
    const httpOptions = {};
    return this.http
      .delete<Chat>(`${this.url}/${userId}/${chatId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong deleting chat');
        })
      );
  }

  showChat(userId: number, chatId: number): Observable<Chat> {
    const httpOptions = {};
    return this.http
      .get<Chat>(
        `${this.url}/users/${userId}/chats/${chatId}`,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong retrieving an chat');
        })
      );
  }

  getHttpOptions() {
    let credentials = this.authService.getCredentials();
    let options = {
      headers: {
        'X-Requested-With': 'XMLHttpRequest',
        Authorization: `Basic ${credentials}`,
      },
    };
    return options;
  }
}
