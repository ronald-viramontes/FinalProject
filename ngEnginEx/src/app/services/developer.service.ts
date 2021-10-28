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

  index(): Observable<Developer[]> {
    return this.http.get<Developer[]>(this.baseUrl).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('DeveloperService.index(): error retrieving developers');
      })
    );
  }


}

