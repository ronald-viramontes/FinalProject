import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import {
  Component,
  ViewEncapsulation,
  ChangeDetectionStrategy,
  OnInit,
} from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { from, Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { HeaderComponent } from '../components/header/header.component';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css'],
})
export class SidenavComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private breakpointObserver: BreakpointObserver,
    private router: Router,
    private userService: UserService,
    private modalService: NgbModal
  ) {}
  ngOnInit(): void {
    this.checkAdminRole();
  }
  activeUser: User = new User();
  adminRights: boolean = false;
  closeResult = '';
  keyword: string = '';
  searchResults: User[] = [];
  emailForm: boolean = false;

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

  sendChat() {
    this.router.navigateByUrl('/sendchat');
  }

  isHandset$: Observable<boolean> = this.breakpointObserver
    .observe(Breakpoints.Handset)
    .pipe(
      map((result) => result.matches),
      shareReplay()
    );

  logOut() {
    this.authService.logout();
  }
  loggedIn() {
    return this.authService.checkLogin();
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
