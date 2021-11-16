import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { JobApplication } from '../models/job-application';
import { JobPost } from '../models/job-post';
import { JobStatus } from '../models/job-status';
import { JobType } from '../models/job-type';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class JobPostService {
  private baseUrl = environment.baseUrl;
  // private baseUrl = '/EnginEx/';
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
        return throwError('JPS.update(): error retrieving JobPosts');
      })
    );
  }
  create(jobPost: JobPost) {
    return this.http.post(this.jobsUrl, jobPost).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'JPS.create(): creating a Job Post was not successful.'
        );
      })
    );
  }
  appById(appId: number) {
    return this.http.get(`${this.appUrl}/app/${appId}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'JPS.create(): creating a Job Post was not successful.'
        );
      })
    );
  }

  createPost(jobPost: JobPost, userId: number) {
    return this.http.post(`${this.jobsUrl}/${userId}`, jobPost).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'JPS.createPost(): creating a Job Post was not successful.'
        );
      })
    );
  }
  createApplication(postId: number, userId: number) {
    return this.http
      .post(`${this.appUrl}/${postId}/${userId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'JPS.createApplication(): Failure creating Job Application'
          );
        })
      );
  }
  approveApplication(jobApplication: JobApplication, status: number) {
    return this.http
      .put<JobApplication>(
        `${this.appUrl}/${jobApplication.id}/${status}`,
        jobApplication,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Job Application approval failed');
        })
      );
  }

  update(jobPost: JobPost) {
    return this.http
      .put<JobPost>(`${this.jobsUrl}/${jobPost.id}`, jobPost)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('JPS.update(): Job Post update unsuccessful');
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

  postsByUser(userId: number) {
    return this.http.get<JobPost[]>(`${this.jobsUrl}/${userId}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'jobPostService.postsByUser(): error retrieving job Posts'
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

  indexByKeyword(keyword: string) {
    return this.http.get<JobPost[]>(`${this.jobsUrl}/search/${keyword}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'JobPostService.indexByKeyword(): error retrieving job posts'
        );
      })
    );
  }

  delete(post: JobPost) {
    return this.http
      .delete(`${this.jobsUrl}/${post.id}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('JobPostService.delete(): error deleting jobPost');
        })
      );
  }
}
