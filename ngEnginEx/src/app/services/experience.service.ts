import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class ExperienceService {
  private baseUrl = 'http://localhost:8091/';

  private url = this.baseUrl + 'api/experiences';
  constructor(private http: HttpClient, private authServ: AuthService) {}
}
