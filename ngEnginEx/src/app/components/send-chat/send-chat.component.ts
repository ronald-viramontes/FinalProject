import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Chat } from 'src/app/models/chat';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ChatService } from 'src/app/services/chat.service';
import { UserService } from 'src/app/services/user.service';
import { ChatComponent } from '../chat/chat.component';

@Component({
  selector: 'app-send-chat',
  templateUrl: './send-chat.component.html',
  styleUrls: ['./send-chat.component.css'],
  template: `<ng-template
    #content
    let-modal
    id="newChatOutside"
  ></ng-template>`,
})
export class SendChatComponent implements OnInit {
  constructor(
    private chatComp: ChatComponent,
    private modalService: NgbModal,
    private authService: AuthService,
    private userService: UserService,
    private chatService: ChatService,
    private router: Router
  ) {}
  @Input() senderUsername: string = '';

  activeUser: User = new User();
  // receiverUsername: string = '';
  newChat: Chat = new Chat();
  closeResult: string = '';
  receiverUsername: string = '';
  @Input() receiver: User | null = null;
  @Input() sender: User | null = null;

  @ViewChild('content', { static: true })
  content!: TemplateRef<any>;

  ngOnInit(): void {
    this.getActiveUser();
    if (this.activeUser) this.modalService.open(this.content);
  }

  open(content: any) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (result) => {
          this.router.navigateByUrl('/home');
          this.closeResult = `Closed with: ${result}`;
        },
        (reason) => {
          this.router.navigateByUrl('/home');
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
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

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  // openForm(content: any) {
  //   this.open(content);
  // }

  create(msg: Chat, receiverUsername: string) {
    msg = this.newChat;
    if (this.activeUser)
      this.chatService
        .create(msg, this.activeUser.id, receiverUsername)
        .subscribe(
          (created) => {
            console.log('Chat created');
            console.log(created);
            this.newChat = new Chat();
            this.receiverUsername = '';
          },
          (fail) => {
            console.error('Something went wrong during chat creation', fail);
          }
        );
    this.router.navigateByUrl('/home');
  }
  cancel() {
    this.newChat = new Chat();
    this.router.navigateByUrl('/home');
  }
}
