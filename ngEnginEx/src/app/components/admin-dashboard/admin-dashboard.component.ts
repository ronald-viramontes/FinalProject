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
  deleted: User | null = null;
  loggedIn() {
    return this.authService.checkLogin();
  }

  removeUser(user: User) {
    this.deleted = user;
    let userId: number = this.deleted.id;
    this.userService.deleteUser(userId).subscribe(
      (data) => {
        this.users = [];
        this.getAllUsers();
      },
      (err) => {
        console.error(err);
      }
    );
  }

  disableUser(user: User) {
    user.enabled = !user.enabled;
    this.selected = user;

    this.userService
      .disableUserAcct(this.selected.username, this.selected)
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
