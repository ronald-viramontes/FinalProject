import { Component } from '@angular/core';
import {
  ModalDismissReasons,
  NgbModal,
  NgbModalConfig,
} from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  template: ` <app-header></app-header>

    <router-outlet></router-outlet>

    <footer></footer>`,
})
export class AppComponent {
  // @Input() modalContent: string = '';
  constructor(
    private authService: AuthService,
    private modalService: NgbModal,
    private config: NgbModalConfig
  ) // private activeModal: NgbActiveModal
  {
    config.animation = true;
    config.backdrop = 'static';
    config.keyboard = false;
  }

  title = 'ngEnginEx';
  closeResult: string = '';

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

  // openContent() {
  //   const modalRef = this.modalService.open(this.modalContent);
  //   modalRef.componentInstance.name = this.modalContent;
  // }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  loggedIn() {
    return this.authService.checkLogin();
  }
}
