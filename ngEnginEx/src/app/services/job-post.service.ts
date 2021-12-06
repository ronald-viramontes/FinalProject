import { HttpClient, HttpHeaders } from '@angular/common/http';
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
  private userUrl = this.baseUrl + 'api';

  constructor(private http: HttpClient, private authService: AuthService) {}

  index(): Observable<JobPost[]> {
    console.log('In call to DB.');
    return this.http.get<JobPost[]>(this.jobsUrl, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('JPS.update(): error retrieving JobPosts');
      })
    );
  }

  appById(appId: number) {
    return this.http
      .get(`${this.appUrl}/app/${appId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'JPS.create(): creating a Job Post was not successful.'
          );
        })
      );
  }
  postById(postId: JobPost): Observable<JobPost> {
    return this.http
      .get<JobPost>(`${this.jobsUrl}/${postId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'JPS.postById: the user Job Post was not successful.'
          );
        })
      );
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
  status() {
    return this.http.get<JobPost[]>(`${this.jobsUrl}/status/`).pipe(
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
  indexType(): Observable<JobType[]> {
    console.log('in call to type DB');
    return this.http.get<JobType[]>(this.typeUrl, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Bad Type Request');
      })
    );
  }

  type(): Observable<JobType[]> {
    console.log('in call to type DB');
    return this.http.get<JobType[]>(this.typeUrl, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Bad Type Request');
      })
    );
  }

  postsByUser(userId: number) {
    return this.http
      .get<JobPost[]>(`${this.jobsUrl}/user/${userId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'jobPostService.postsByUser(): error retrieving job Posts'
          );
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
  //VISITOR GET MAPPINGS --->
  visitorStatus(): Observable<JobStatus[]> {
    return this.http.get<JobStatus[]>(`${this.userUrl}/stats`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Bad Type Request');
      })
    );
  }
  visitorTypes(): Observable<JobType[]> {
    return this.http.get<JobType[]>(`${this.userUrl}/types`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Bad Type Request');
      })
    );
  }

  visitorIndex(): Observable<JobPost[]> {
    console.log('In call to DB.');
    return this.http.get<JobPost[]>(`${this.userUrl}/posts`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('JPS.update(): error retrieving JobPosts');
      })
    );
  }
  //<--- VISITOR GET MAPPINGS

  create(jobPost: JobPost) {
    return this.http.post(this.jobsUrl, jobPost, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'JPS.create(): creating a Job Post was not successful.'
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
          return throwError('Failure creating Job Application');
        })
      );
  }

  approveApplication(jobApp: JobApplication, approval: number) {
    const httpOptions = {};
    return this.http
      .put<JobApplication>(
        `${this.appUrl}/${jobApp.id}/${approval}`,
        jobApp,
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
    const httpOptions = {};
    return this.http
      .put<JobPost>(
        `${this.jobsUrl}/${jobPost.id}`,
        jobPost,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('JPS.update(): Job Post update unsuccessful');
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
  //my methods
  createPost(jobPost: JobPost) {
    const httpOptions = {
      ResponseHeader: 'application/json',
    };
    return this.http
      .post<JobPost>(`${this.userUrl}/userjobs`, jobPost, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'JPS.createPost(): creating a Job Post was not successful.'
          );
        })
      );
  }

  editPost(jobPost: JobPost, postId: number) {
    const httpOptions = {};
    return this.http
      .put<JobPost>(
        `${this.jobsUrl}/${jobPost.id}`,
        jobPost,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('JPS.update(): Job Post update unsuccessful');
        })
      );
  }

  deletePost(jobPost: JobPost) {
    const httpOptions = {};
    return this.http
      .delete(`${this.userUrl}/userjobs/${jobPost.id}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('JobPostService.delete(): error deleting jobPost');
        })
      );
  }

  createApp(newApp: JobApplication) {
    const httpOptions = {};
    return this.http.post(`${this.userUrl}/userapps`, newApp).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'JPS.createApplication(): Failure creating Job Application'
        );
      })
    );
  }

  appDecision(jobApp: JobApplication, postId: JobPost) {
    const httpOptions = {};
    return this.http
      .put<JobApplication>(`${this.userUrl}/appdecision/${postId}`, jobApp)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Job Application approval failed');
        })
      );
  }

  deleteApp(appId: number) {
    const httpOptions = {};
    return this.http.delete(`${this.userUrl}/userapps/${appId}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          'JPS.createApplication(): Failure creating Job Application'
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
