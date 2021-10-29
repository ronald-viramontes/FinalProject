import { Component, OnInit } from '@angular/core';
import { Developer } from 'src/app/models/developer';
import { AuthService } from 'src/app/services/auth.service';
import { DeveloperService } from 'src/app/services/developer.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {

  constructor(private authService: AuthService, private userService: UserService, private developerService: DeveloperService) { }

  developerLoggedIn: Developer = new Developer();
  ngOnInit(): void {
  }

  loggedIn(){
    return this.authService.checkLogin();
  }
}
