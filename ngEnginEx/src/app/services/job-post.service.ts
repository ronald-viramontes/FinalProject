import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { JobApplication } from '../models/job-application';
import { JobPost } from '../models/job-post';
import { JobStatus } from '../models/job-status';
import { JobType } from '../models/job-type';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class JobPostService {
  private baseUrl = 'http://localhost:8091/';
  private jobsUrl = this.baseUrl + 'api/jobs';
  private statusUrl = this.baseUrl + 'api/jobstatus';
  private typeUrl = this.baseUrl + 'api/jobtypes';
  private appUrl = this.baseUrl + 'api/apps';

  constructor(private http: HttpClient, private authService: AuthService) {}

  index(): Observable<JobPost[]> {
    console.log('In call to DB.');
    return this.http.get<JobPost[]>(this.jobsUrl).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Bad Post Request');
      })
    );
  }
  create(jobPost: JobPost) {
    return this.http.post(this.jobsUrl, jobPost).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('creating a Job Post was not successful.');
      })
    );
  }
  createApplication(jobApplication: JobApplication) {
    console.log(jobApplication);

    return this.http
      .post(
        `${this.appUrl}/${jobApplication.jobPost.id}`,
        jobApplication,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Failure creating Job Application');
        })
      );
  }
  approveApplication(jobApplication: JobApplication) {
    return this.http.put<JobApplication>(`${this.appUrl}/${jobApplication.id}`, jobApplication).pipe(
      catchError((err: any) =>{
        console.log(err);
        return throwError('Job Application approval failed')
      })
    )
  }

  update(jobPost: JobPost) {
    return this.http
      .put<JobPost>(`${this.jobsUrl}/${jobPost.id}`, jobPost)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Job Post update unsuccessful');
        })
      );
  }
  indexStatus(): Observable<JobStatus[]> {
    console.log('in call to type DB');
    return this.http.get<JobStatus[]>(this.statusUrl).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Bad Type Request');
      })
    );
  }

  indexType(): Observable<JobType[]> {
    console.log('in call to type DB');
    return this.http.get<JobType[]>(this.typeUrl).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Bad Type Request');
      })
    );
  }

  showByClient(clientId: number) {
    return this.http.get<JobPost[]>(`${this.jobsUrl}/client/${clientId}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'jobPostService.showByClient(): error retrieving job Posts'
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
  indexByStatus(status: string) {
    return this.http.get<JobPost[]>(`${this.jobsUrl}/status/${status}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'jobPostService.indexByStatus(): error retrieving job posts'
        );
      })
    );
  }
}
