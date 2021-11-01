import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Skill } from '../models/skill';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class SkillService {
  private baseUrl = 'http://localhost:8091/';
  private url = this.baseUrl + 'api/skills';

  constructor(private http: HttpClient, private authService: AuthService) {}

  index(): Observable<Skill[]> {
    return this.http.get<Skill[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Something went wrong finding skills list');
      })
    );
  }

  devSkillIndex(dId: number): Observable<Skill[]> {
    return this.http
      .get<Skill[]>(`${this.url}/developers/${dId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            'Something went wrong finding developer skills list'
          );
        })
      );
  }

  create(newSkill: Skill) {
    console.log(newSkill);
    return this.http
      .post<Skill>(`${this.url}`, newSkill, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong with creation of skill');
        })
      );
  }

  update(skill: Skill, sId: number) {
    const httpOptions = {};
    return this.http
      .put<Skill>(`${this.url}/${sId}`, skill, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong with update of skill');
        })
      );
  }

  destroy(sId: number) {
    const httpOptions = {};
    return this.http
      .delete<Skill>(`${this.url}/${sId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong deleting a skill');
        })
      );
  }

  show(sId: number): Observable<Skill> {
    const httpOptions = {};
    return this.http
      .get<Skill>(`${this.url}/${sId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong finding the skill');
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
