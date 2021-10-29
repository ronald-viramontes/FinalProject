import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { JobPost } from '../models/job-post';

@Injectable({
  providedIn: 'root',
})
export class JobPostService {
  private baseUrl = 'http://localhost:8091/';
  private url = this.baseUrl + 'api/jobs';

  constructor(private http: HttpClient) {}

  index(): Observable<JobPost[]> {
    console.log('In call to DB.');
    return this.http.get<JobPost[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Bad');
      })
    );
  }
}
