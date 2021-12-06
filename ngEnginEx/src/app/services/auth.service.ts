import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { User } from '../models/user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // private baseUrl = '/EnginEx/';
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    // Make credentials
    const credentials = this.generateBasicAuthCredentials(username, password);
    // Send credentials as Authorization header (this is spring security convention for basic auth)
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };

    // create request to authenticate credentials
    return this.http.get(this.baseUrl + 'authenticate', httpOptions).pipe(
      tap((res) => {
        localStorage.setItem('credentials', credentials);
        return res;
      }),
      catchError((err: any) => {
        console.error(err);
        return throwError('AuthService.login(): Error logging in.');
      })
    );
  }

  register(user: User) {
    // create request to register a new account
    console.log(user);
    return this.http.post(this.baseUrl + 'register', user).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('AuthService.register(): error registering user.');
      })
    );
  }

  logout() {
    localStorage.removeItem('credentials');
  }

  checkLogin() {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

  generateBasicAuthCredentials(username: string, password: string) {
    return btoa(`${username}:${password}`);
  }

  getCredentials() {
    return localStorage.getItem('credentials');
  }
}
