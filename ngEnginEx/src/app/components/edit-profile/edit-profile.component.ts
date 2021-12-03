import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css'],
})
export class EditProfileComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router
  ) {}

  activeUser: User = new User();
  editUser: User = new User();
  ngOnInit(): void {
    this.getActiveUser();
  }

  getActiveUser() {
    let creds = this.authService.getCredentials();
    if (creds != null) {
      creds = atob(creds);
      let unArr = creds.split(':');
      let username = unArr[0];
      this.userService.show(username).subscribe(
        (data) => {
          this.activeUser = data;
          this.setEdit();
        },
        (err) => {
          console.error(err);
        }
      );
    }
  }

  setEdit() {
    Object.assign(this.editUser, this.activeUser);
  }

  loggedIn() {
    return this.authService.checkLogin();
  }

  cancel() {
    this.router.navigateByUrl('/home');
  }

  edit(editUser: User) {
    editUser.applications = [];
    editUser.posts = [];
    // console.log(editUser);
    this.userService.edit(editUser.id, editUser).subscribe(
      (data) => {
        alert('Profile Updated');
        this.router.navigateByUrl('/home');
      },
      (err) => {
        console.error(err);
      }
    );
  }

  disableProfile() {
    if (confirm('Are you sure you want to deactivate your account?')) {
      this.activeUser.enabled = false;
      this.activeUser.applications = [];
      this.activeUser.posts = [];
      this.userService.edit(this.activeUser.id, this.activeUser).subscribe(
        (data) => {
          this.authService.logout();
          this.router.navigateByUrl('/home');
        },
        (err) => {
          console.error(err);
        }
      );
    }
  }
}
