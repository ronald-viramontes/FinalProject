import { KeyedWrite } from '@angular/compiler';
import { Component, Input, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-search',
  templateUrl: './user-search.component.html',
  styleUrls: ['./user-search.component.css'],
})
export class UserSearchComponent implements OnInit {
  constructor(
    private userService: UserService,
    private authService: AuthService,
    private modalService: NgbModal
  ) {}

  closeResult = '';
  keyword: string = '';
  searchResults: User[] = [];
  emailForm: boolean = false;
  activeUser: User = new User();

  ngOnInit(): void {
    if (this.loggedIn()) {
      this.getActiveUser();
    }
  }

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

  loggedIn() {
    return this.authService.checkLogin();
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
