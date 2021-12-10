import { Component, Input, OnInit } from '@angular/core';
import { Navigation, Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { JobPostService } from 'src/app/services/job-post.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  template: `
    <nav class="nav-bar">
      <div class="navbar-brand">
        <a class="navbar-item">
          <img
            src="assets/EnginEx.png"
            style="border-radius: 50%;"
            height="50"
          />
        </a>
      </div>
    </nav>
  `,
})
export class HeaderComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private router: Router,
    private postService: JobPostService,
    private userService: UserService,
    private modalService: NgbModal
  ) {}

  adminRights: boolean = false;
  user: User = new User();
  activeUser: User = new User();

  ngOnInit(): void {
    this.checkAdminRole();
  }

  loggedIn() {
    return this.authService.checkLogin();
  }

  logOut() {
    this.authService.logout();
  }

  login(user: User) {
    this.authService.login(user.username, user.password).subscribe(
      (data) => {
        location.reload();
        this.router.navigateByUrl('/home');
        this.user = new User();
      },
      (err) => {
        console.error('LoginComponent.login(): error logging in');
        console.error(err);
      }
    );
  }

  register() {
    this.router.navigateByUrl('/register');
  }

  sendChat() {
    this.router.navigateByUrl('/sendchat');
  }

  checkAdminRole() {
    let creds = this.authService.getCredentials();
    if (creds != null) {
      creds = atob(creds);
      let unArr = creds.split(':');
      let username = unArr[0];
      this.userService.show(username).subscribe(
        (data) => {
          this.activeUser = data;
          if (this.activeUser.role == 'ADMIN') {
            this.adminRights = true;
          }
        },
        (err) => {
          console.error(err);
        }
      );
    }
  }
  closeResult = '';
  keyword: string = '';
  searchResults: User[] = [];
  emailForm: boolean = false;
  loggedInClass() {
    if (this.loggedIn()) {
      return 'loggedIn';
    } else {
      return 'loggedOut';
    }
  }
  search(skillKeyword: string) {
    this.userService.indexBySkill(skillKeyword).subscribe(
      (data) => {
        this.searchResults = data;
      },
      (err) => {
        console.error(err);
      }
    );
  }
  email(address: string) {
    this.emailForm = true;
  }
  checkKeyword(key: string) {
    key = key.toUpperCase();
    let normKeyword = this.keyword.toUpperCase();
    return key.includes(normKeyword);
  }
  open(content: any) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        }
      );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
}
