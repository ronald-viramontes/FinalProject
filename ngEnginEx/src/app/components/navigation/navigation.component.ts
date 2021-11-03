import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JobPost } from 'src/app/models/job-post';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { JobPostService } from 'src/app/services/job-post.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router, private postService: JobPostService) { }
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
        location.reload();
        this.router.navigateByUrl('/home');
        this.user = new User();
      },
      err => {
        console.error('LoginComponent.login(): error logging in');
        console.error(err);
      }
    )
  }

  register(){
    this.router.navigateByUrl('/register');
  }


}
