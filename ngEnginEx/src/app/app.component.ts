import { Component, ViewEncapsulation } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class AppComponent {
  title = 'ngEnginEx';

  constructor(private authService: AuthService) {}

  loggedIn() {
    return this.authService.checkLogin();
  }
}
