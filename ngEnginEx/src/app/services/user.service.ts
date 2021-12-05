import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient, private authService: AuthService) {}

  private baseUrl = environment.baseUrl;
  // private baseUrl = '/EnginEx/';
  private userUrl = this.baseUrl + 'api/users';
  private adminUrl = this.baseUrl + 'api/admin';

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

  userIndex() {
    return this.http.get<User[]>(this.userUrl, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('UserService.userIndex(): error retrieving users');
      })
    );
  }

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

  disableUserAcct(username: string, user: User) {
    return this.http
      .put<User>(
        `${this.adminUrl}/eord/${username}`,
        user,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('UserService.edit(): error editing user');
        })
      );
  }
  deleteUser(userId: number) {
    return this.http
      .delete<User>(`${this.adminUrl}/delete/${userId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('UserService.deleteUser(): error deleting user');
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
