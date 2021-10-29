import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Client } from '../models/client';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  private baseUrl = 'http://localhost:8091/';

  private url = this.baseUrl + 'api/clients';
  constructor(private http: HttpClient, private authService: AuthService) {}

  index(): Observable<Client[]> {
    return this.http.get<Client[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );
  }

  create(newClient: Client) {
    console.log(newClient);
    return this.http
      .post<Client>(`${this.url}`, newClient, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  update(userId: number, clientId: number, client: Client) {
    const httpOptions = {};
    return this.http
      .put<Client>(
        `${this.baseUrl}api/users/${userId}/clients/${clientId} `,
        client,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong with update of client');
        })
      );
  }

  destroy(clientId: number) {
    const httpOptions = {};
    return this.http
      .delete<Client>(`${this.url}/${clientId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Something went wrong deleting client');
        })
      );
  }

  show(clientId: number): Observable<Client> {
    const httpOptions = {};
    return this.http
      .get<Client>(`${this.url}/${clientId}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
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
