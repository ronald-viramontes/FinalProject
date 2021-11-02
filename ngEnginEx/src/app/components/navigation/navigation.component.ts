import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }
  user: User = new User();
  ngOnInit(): void {
  }

  loggedIn(){
    return this.authService.checkLogin();
  }

  logOut(){
    this.authService.logout();
  }

  login(user: User){
    this.authService.login(user.username, user.password).subscribe(
      data => {
        this.router.navigateByUrl('/home')
      },
      err => {
        console.error('LoginComponent.login(): error logging in');
        console.error(err);
      }
    )
  }

}
