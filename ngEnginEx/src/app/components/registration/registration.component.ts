import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}
  newUser: User = new User();
  duplicateUsername: boolean = false;

  ngOnInit(): void {}

  register() {
    this.authService.register(this.newUser).subscribe(
      (data) => {
        console.log('RegisterComponent.register(): user registered.');
        this.authService
          .login(this.newUser.username, this.newUser.password)
          .subscribe(
            (next) => {
              console.log('RegisterComponent.register(): user logged in.');
              this.router.navigateByUrl('/home')
            },
            (error) => {
              console.error('RegisterComponent.register(): error logging in.');
            }
          );
      },
      (err) => {
        console.error('RegisterComponent.register(): error registering.');
        console.error(err);
        this.duplicateUsername = true;
      }
    );
  }

  loggedIn(){
    return this.authService.checkLogin();
  }

  update() {}

  cancel(){
    this.router.navigateByUrl('/home');
  }
}
