import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Experience } from '../models/experience';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class ExperienceService {
  private baseUrl = 'http://localhost:8091/';

  private url = this.baseUrl + 'api/experiences';
  constructor(private http: HttpClient, private authService: AuthService) {}

  index(): Observable<Experience[]> {
    return this.http.get<Experience[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Something went wrong retrieving the exp list');
      })
    );
  }

  create(newExperience: Experience, devId: number) {
    console.log(newExperience);
    return this.http
      .post<Experience>(
        `${this.url}/${devId}`,
        newExperience,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  update(expId: number, devId: number, exp: Experience) {
    const httpOptions = {};
    return this.http
      .put<Experience>(
        `${this.url}/${devId}/${expId}`,
        exp,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong with update of exp');
        })
      );
  }

  destroy(expId: number, devId: number) {
    const httpOptions = {};
    return this.http
      .delete<Experience>(
        `${this.url}/${devId}/${expId}`,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong deleting exp');
        })
      );
  }

  show(expId: number): Observable<Experience> {
    const httpOptions = {};
    return this.http
      .get<Experience>(`${this.url}/${expId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong retrieving an exp by devId');
        })
      );
  }

  devExperienceIndex(dId: number): Observable<Experience[]> {
    return this.http
      .get<Experience[]>(`${this.url}/${dId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Something went wrong finding developer skills list'
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
