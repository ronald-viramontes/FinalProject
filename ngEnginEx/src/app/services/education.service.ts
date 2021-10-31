import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Education } from '../models/education';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class EducationService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  private baseUrl = 'http://localhost:8091/api/educations'

  getHttpOptions() {
    let credentials = this.authService.getCredentials();
    let options = {
      headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Authorization': `Basic ${credentials}`
      }
    };
    return options;
  }

  index(): Observable<Education[]> {
    return this.http.get<Education[]>(this.baseUrl).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('EducationService.index(): error retrieving educations');
      })
    );
  }

  show(devId: number) {
    return this.http.get<Education[]>(this.baseUrl + '/' + devId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('EducationService.show: error retrieving educations');
      })
    );
  }

  edit(education: Education, edId: number, devId: number) {
    return this.http.put<Education>(`${this.baseUrl}/${devId}/${edId}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('EducationService,edit(): error editing education')
      })
    );
  }

  delete(edId: number) {
    return this.http.delete(this.baseUrl + '/' + edId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('EducationService,delete(): error deleting education')
      })
    );
  }
}
