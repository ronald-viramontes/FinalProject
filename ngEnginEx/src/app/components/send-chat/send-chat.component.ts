import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
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

  users: User[] = [];

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
    this.scrollUsernames();
    if (this.activeUser) this.modalService.open(this.content);
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

  scrollUsernames() {
    this.userService.userIndex().subscribe(
      (userList) => {
        console.log('List obtained');
        this.users = userList;
      },
      (fail) => {
        console.error('Something went wrong', fail);
      }
    );
  }

  open(content: any) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
          this.router.navigateByUrl('/home');
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
          this.newChat = new Chat();
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

  username: string = '';
  rec: User = new User();
  create(msg: Chat, receiver: User) {
    msg = this.newChat;
    this.username = receiver.username;
    console.log('Username', this.username);
    if (this.activeUser)
      this.chatService.create(msg, this.activeUser.id, this.username).subscribe(
        (created) => {
          console.log('Chat created');
          this.newChat = new Chat();
          this.receiverUsername = '';
          this.router.navigateByUrl('/home');
        },
        (fail) => {
          console.error('Something went wrong during chat creation', fail);
        }
      );
  }
  cancel() {
    this.newChat = new Chat();
    this.router.navigateByUrl('/home');
  }
}
