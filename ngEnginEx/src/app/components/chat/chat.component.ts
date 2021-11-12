import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Chat } from 'src/app/models/chat';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ChatService } from 'src/app/services/chat.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit {
  closeResult = '';
  constructor(
    private chatService: ChatService,
    private router: Router,
    private authService: AuthService,
    private userService: UserService,
    private modalService: NgbModal
  ) {}

  ngOnInit() {
    if (this.activeUser && this.sentChats) {
      this.loadSentChats(this.activeUser.id);
    }
  }
  open(content: any) {
    this.newChat = new Chat();
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

  @Input() activeUser: User | null = null;
  @Input() receivedChats: Chat[] = [];
  @Input() sentChats: Chat[] = [];

  chats: Chat[] = [];
  selected: Chat | null = null;
  chatReply: Chat | null = null;
  newChat: Chat = new Chat();
  reply: Chat = new Chat();
  editChat: Chat | null = null;
  chat: Chat | null = null;
  chatDetail: Chat | null = null;
  receiverUsername: string | null = null;
  addButton: boolean = false;

  create(newChat: Chat) {
    if (this.activeUser && this.receiverUsername)
      this.chatService
        .create(newChat, this.activeUser.id, this.receiverUsername)
        .subscribe(
          (created) => {
            console.log('Chat created');
            console.log(created);
            this.newChat = new Chat();
            this.addButton = false;
            this.receiverUsername = null;
            if (this.activeUser) this.loadSentChats(this.activeUser.id);
            if (this.activeUser) this.loadReceivedChats();
          },
          (fail) => {
            console.error('Something went wrong during chat creation', fail);
          }
        );
  }
  createReply(chatId: number) {
    if (this.activeUser && this.reply)
      this.chatService
        .createReply(this.reply, this.activeUser.id, chatId)
        .subscribe(
          (created) => {
            console.log('Chat created');
            console.log(created);
            this.reply = new Chat();
            this.chatReply = null;
            this.addButton = false;
            if (this.activeUser) this.loadSentChats(this.activeUser.id);
            if (this.activeUser) this.loadReceivedChats();
          },
          (fail) => {
            console.error('Something went wrong during chat creation', fail);
          }
        );
  }

  updateChat(editChat: Chat) {
    if (this.activeUser && editChat.receiver)
      this.chatService
        .update(editChat.id, this.activeUser.id, editChat.receiver.id, editChat)
        .subscribe(
          (updated) => {
            console.log('User Chat has updated successfully');
            this.editChat = null;
            if (this.activeUser) this.loadSentChats(this.activeUser.id);
            if (this.activeUser) this.loadReceivedChats();
          },
          (fail) => {
            console.error('Something went wrong with updating chat', fail);
          }
        );
  }

  loadReceivedChats() {
    this.addButton = false;
    if (this.activeUser)
      this.chatService
        .receiverIndex(this.activeUser.id, this.activeUser.id)
        .subscribe(
          (data) => {
            this.receivedChats = data;
            // this.editExp = null;
            // this.chatDetail = null;
          },
          (err) => {
            console.error('Error retrieving skill list', err);
            console.error(err);
          }
        );
  }
  loadSentChats(userId: number) {
    this.addButton = false;
    this.chatService.senderIndex(userId).subscribe(
      (data) => {
        this.sentChats = data;
        // this.editExp = null;
        // this.chatDetail = null;
      },
      (err) => {
        console.error('Error retrieving skill list', err);
        console.error(err);
      }
    );
  }

  deleteChat(chatId: number) {
    if (this.activeUser)
      this.chatService.destroy(chatId, this.activeUser.id).subscribe(
        (success) => {
          this.chat = null;
          if (this.activeUser) this.loadSentChats(this.activeUser.id);
          if (this.activeUser) this.loadReceivedChats();
          console.log('Successfully removed chat', success);
        },
        (fail) => {
          console.error('Failed to remove user', fail);
        }
      );
  }

  selectChat(chat: Chat) {
    this.selected = chat;
    this.editChat = null;
  }

  setEditChat(ch: Chat) {
    this.editChat = ch;
  }
  setReplyChat(chat: Chat) {
    this.chatReply = chat;
  }
  setAddButton() {
    this.addButton = true;
  }
}
