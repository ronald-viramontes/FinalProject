import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from 'src/app/services/auth.service';
import { ChatService } from 'src/app/services/chat.service';
import { UserService } from 'src/app/services/user.service';
import { ChatComponent } from '../chat/chat.component';

@Component({
  selector: 'app-chat-sent',
  templateUrl: './chat-sent.component.html',
  styleUrls: ['./chat-sent.component.css'],
})
export class ChatSentComponent extends ChatComponent implements OnInit {
  constructor(
    chatService: ChatService,
    router: Router,
    authService: AuthService,
    userService: UserService,
    modalService: NgbModal
  ) {
    super(chatService, router, authService, userService, modalService);
  }

  ngOnInit(): void {
    super.ngOnInit();
  }
}
