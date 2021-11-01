import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css'],
})
export class UserHomeComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private userService: UserService
  ) {}

  // developerLoggedIn: Developer = new Developer();
  ngOnInit(): void {}

  loggedIn() {
    return this.authService.checkLogin();
  }
}
