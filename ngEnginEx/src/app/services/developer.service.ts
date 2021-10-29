import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Developer } from '../models/developer';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DeveloperService {


  constructor(private http: HttpClient, private authService: AuthService) { }

  private baseUrl = 'http://localhost:8091/api/developers'

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

  index(): Observable<Developer[]> {
    return this.http.get<Developer[]>(this.baseUrl).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('DeveloperService.index(): error retrieving developers');
      })
    );
  }

  show(devId: number): Observable<Developer>{
    return this.http.get<Developer>(this.baseUrl+'/'+devId).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError('DeveloperService.show(): error retrieving developer')
      })
    );
  }

  // delete(devId: number){
  //   return this.http.delete(this.baseUrl+'/'+devId, this.getHttpOptions()).pipe(
  //     catchError((err: any) => {
  //       console.log(err);
  //       return throwError('DeveloperService.delete(): error deleting developer');
  //     })
  //   );
  // }

}

