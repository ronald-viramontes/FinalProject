import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from '../models/user';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient, private authService: AuthService) {}

  private baseUrl = environment.baseUrl;
  // private baseUrl = '/EnginEx/';
  private userUrl = this.baseUrl + 'api/users';

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

  // index(): Observable<Developer[]> {
  //   return this.http.get<Developer[]>(this.baseUrl).pipe(
  //     catchError((err: any) => {
  //       console.log(err);
  //       return throwError('DeveloperService.index(): error retrieving developers');
  //     })
  //   );
  // }

  show(username: string): Observable<User> {
    return this.http
      .get<User>(`${this.baseUrl}api/user/${username}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('UserService.show(): error retrieving user');
        })
      );
  }

  edit(userId: number, user: User) {
    return this.http
      .put<User>(`${this.userUrl}/${userId}`, user, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('UserService.edit(): error editing user');
        })
      );
  }

  indexBySkill(keyword: string) {
    return this.http.get<User[]>(`${this.userUrl}/skills/${keyword}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('UserService.indexBySkill(): error retrieving users');
      })
    );
  }
}
