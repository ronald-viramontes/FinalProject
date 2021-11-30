import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { JobApplication } from '../models/job-application';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { JobPost } from '../models/job-post';
@Injectable({
  providedIn: 'root',
})
export class JobApplicationService {
  private baseUrl = environment.baseUrl;
  // private baseUrl = '/EnginEx/';

  private userappUrl = this.baseUrl + 'api/userapps';
  private appUrl = this.baseUrl + 'api/apps';

  constructor(private http: HttpClient, private authService: AuthService) {}

  allAppsIndex(): Observable<JobApplication[]> {
    return this.http
      .get<JobApplication[]>(`${this.appUrl}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong finding skills list');
        })
      );
  }

  userAppsIndex(userId: number): Observable<JobApplication[]> {
    const httpOptions = {};
    return this.http
      .get<JobApplication[]>(
        `${this.userappUrl}/${userId}`,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong finding applications list');
        })
      );
  }

  appById(appId: number) {
    const httpOptions = {};
    return this.http
      .get<JobApplication>(
        `${this.userappUrl}/app/${appId}`,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Application not found, or invalid request!');
        })
      );
  }

  newJobApplication(jobPost: JobPost): Observable<JobApplication> {
    const httpOptions = {};
    return this.http
      .post<JobApplication>(
        `${this.userappUrl}/new/${jobPost.id}`,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Application not created due to failure');
        })
      );
  }

  approveApp(appId: number, app: JobApplication) {
    const httpOptions = {};
    return this.http
      .put<JobApplication>(
        `${this.userappUrl}/approved/${appId}`,
        app,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Job Application approval failed');
        })
      );
  }

  deniedApp(appId: number, app: JobApplication) {
    const httpOptions = {};
    return this.http
      .put<JobApplication>(
        `${this.userappUrl}/denied/${appId}`,
        app,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Job Application denial failed');
        })
      );
  }

  deleteApp(appId: number) {
    const httpOptions = {};
    return this.http
      .delete(`${this.userappUrl}/${appId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Application not deleted.  Something went wrong.');
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
