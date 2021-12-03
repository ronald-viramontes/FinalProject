import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css'],
})
export class AdminDashboardComponent implements OnInit {
  constructor(
    private userService: UserService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.getAllUsers();
  }

  @Input() activeUser: User | null = null;
  users: User[] = [];
  selected: User | null = null;

  selectUser(user: User) {
    this.selected = user;
  }

  getAllUsers() {
    this.userService.userIndex().subscribe(
      (data) => {
        this.users = data;
      },
      (err) => {
        console.error(
          'Something went wrong with getting the list of users!',
          err
        );
      }
    );
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
        },
        (err) => {
          console.error(err);
        }
      );
    }
  }

  loggedIn() {
    return this.authService.checkLogin();
  }

  disableUser(user: User) {
    this.selected = user;
    if (this.selected) {
      // this.selected.applications = [];
      // this.selected.posts = [];
      // this.selected.educations = [];
      // this.selected.experiences = [];
      // this.selected.applications = [];
      // this.selected.posts = [];
      // this.selected.jobStatuses;
      // this.selected.skills = [];
      // this.selected.sentMessages = [];
      // this.selected.receivedMessages = [];

      this.userService
        .disableUserAcct(this.selected.id, this.selected.username)
        .subscribe(
          (data) => {
            this.users = [];
            this.getAllUsers();
          },
          (err) => {
            console.error(err);
          }
        );
    }
  }
}
