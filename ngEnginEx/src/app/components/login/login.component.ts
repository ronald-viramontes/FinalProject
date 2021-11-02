import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  isRegistering: boolean = false;
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
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
