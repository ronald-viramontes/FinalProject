import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-search',
  templateUrl: './user-search.component.html',
  styleUrls: ['./user-search.component.css']
})
export class UserSearchComponent implements OnInit {
  constructor(private userService: UserService, private authService: AuthService) { }

  keyword: string = '';
  searchResults: User[] = [];
  emailForm: boolean = false;
  activeUser: User = new User();

  ngOnInit(): void {
    if(this.loggedIn()){
      this.getActiveUser();
    }
  }

  search(skillKeyword: string){
    this.userService.indexBySkill(skillKeyword).subscribe(
      data => {
        this.searchResults = data;
      },
      err => {
        console.error(err);
      }
    );
  }

  email(address: string){
    this.emailForm = true;

  }

  loggedIn(){
    return this.authService.checkLogin();
  }

  getActiveUser(){
    let creds = this.authService.getCredentials();
    if(creds != null){
      creds = atob(creds);
      let unArr = creds.split(':');
      let username = unArr[0]
      this.userService.show(username).subscribe(
        data => {
          this.activeUser = data;
        },
        err => {
          console.error(err);
        }
      );
    }
  }
}
