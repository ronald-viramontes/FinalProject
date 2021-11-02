import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { JobDetail } from '../models/job-detail';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class JobDetailService {
  private baseUrl = 'http://localhost:8091/';
  private url = this.baseUrl + 'api/jobdetails';
  constructor(private http: HttpClient, private authService: AuthService) {}

  index(): Observable<JobDetail[]> {
    return this.http.get<JobDetail[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Something went wrong finding jobDetails list');
      })
    );
  }

  create(newJobDetail: JobDetail, appId: number) {
    console.log(newJobDetail);
    return this.http
      .post<JobDetail>(
        `${this.url}/${appId}`,
        newJobDetail,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong with creation of jobDetail');
        })
      );
  }

  update(jId: number, userId: number, jobDetail: JobDetail) {
    const httpOptions = {};
    return this.http
      .put<JobDetail>(
        `${this.url}/${userId}/${jId}`,
        jobDetail,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong with update of jobDetail');
        })
      );
  }

  destroy(jId: number, userId: number) {
    const httpOptions = {};
    return this.http
      .delete<JobDetail>(`${this.url}/${userId}/${jId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong deleting a jobDetail');
        })
      );
  }

  show(jId: number): Observable<JobDetail> {
    const httpOptions = {};
    return this.http
      .get<JobDetail>(`${this.url}/${jId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong finding the jobDetail');
        })
      );
  }

  userAppJobDetail(appId: number): Observable<JobDetail> {
    return this.http
      .get<JobDetail>(
        `${this.url}/applications/${appId}`,
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
