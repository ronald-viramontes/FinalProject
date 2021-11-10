import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { JobApplicationComment } from '../models/job-application-comment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class JobApplicationCommentService {
  private baseUrl = environment.baseUrl;
  // private baseUrl = '/EnginEx/';
  private url = this.baseUrl + 'api/comments';
  constructor(private http: HttpClient, private authService: AuthService) {}

  index(): Observable<JobApplicationComment[]> {
    return this.http
      .get<JobApplicationComment[]>(this.url, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong finding comment list');
        })
      );
  }

  create(newComment: JobApplicationComment, userId: number, appId: number) {
    console.log(newComment);
    return this.http
      .post<JobApplicationComment>(
        `${this.url}/${userId}/${appId}`,
        newComment,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong with creation of comment');
        })
      );
  }

  createReply(
    inReplyToComment: JobApplicationComment,
    userId: number,
    appId: number
  ) {
    console.log(inReplyToComment);
    return this.http
      .post<JobApplicationComment>(
        `${this.url}/${userId}/${appId}`,
        inReplyToComment,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong with creation of comment');
        })
      );
  }

  update(
    updateComment: JobApplicationComment,
    appId: number,
    commentId: number
  ) {
    const httpOptions = {};
    return this.http
      .put<JobApplicationComment>(
        `${this.url}/${appId}/${commentId}`,
        updateComment,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Something went wrong with update of updateComment'
          );
        })
      );
  }

  destroy(appId: number, commentId: number) {
    const httpOptions = {};
    return this.http
      .delete<JobApplicationComment>(
        `${this.url}/${appId}/${commentId}`,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong deleting a comment');
        })
      );
  }

  show(userId: number): Observable<JobApplicationComment[]> {
    const httpOptions = {};
    return this.http
      .get<JobApplicationComment[]>(
        `${this.url}/${userId}`,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong finding the comment');
        })
      );
  }

  userAppComments(
    userId: number,
    appId: number
  ): Observable<JobApplicationComment[]> {
    return this.http
      .get<JobApplicationComment[]>(
        `${this.url}/users/${userId}/applications/${appId}`,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Something went wrong finding developer jobDetails list'
          );
        })
      );
  }
  userComment(
    userId: number,
    appId: number,
    commentId: number
  ): Observable<JobApplicationComment> {
    return this.http
      .get<JobApplicationComment>(
        `${this.url}/users/${userId}/applications/${appId}/${commentId}`,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Something went wrong finding developer jobDetails list'
          );
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
