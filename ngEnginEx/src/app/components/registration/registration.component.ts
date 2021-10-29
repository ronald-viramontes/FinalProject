import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private authService: AuthService) { }
  newUser: User = new User();

  ngOnInit(): void {
  }

  register(){
    this.authService.register(this.newUser).subscribe(
      data => {
        console.log('RegisterComponent.register(): user registered.');
        this.authService.login(this.newUser.username, this.newUser.password).subscribe(
          next => {
            console.log('RegisterComponent.register(): user logged in.');
          },
          error => {
            console.error('RegisterComponent.register(): error logging in.');
          }
        );
      },
      err => {
        console.error('RegisterComponent.register(): error registering.');
        console.error(err);
      }
    );
  }

  update(){
    this.newUser.client.firstName = this.newUser.developer.firstName;
    this.newUser.client.lastName = this.newUser.developer.lastName;
    this.newUser.client.email = this.newUser.developer.email;
    this.newUser.client.imageUrl = this.newUser.developer.imageUrl;
    this.newUser.client.phoneNumber = this.newUser.developer.phoneNumber;
  }
}
