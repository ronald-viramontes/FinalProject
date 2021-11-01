import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from '../models/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  private baseUrl = 'http://localhost:8091/api/users'

  getHttpOptions(){
    let credentials = this.authService.getCredentials();
    let options = {
      headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Authorization': `Basic ${credentials}`
      }
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

  show(username: string): Observable<User>{
    return this.http.get<User>('http://localhost:8091/api/user/'+username, this.getHttpOptions()).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError('DeveloperService.show(): error retrieving user')
      })
    );
  }
}
