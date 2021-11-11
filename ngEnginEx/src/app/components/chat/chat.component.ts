import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  constructor(
    private chatService: ChatService,
    private router: Router,
    private authService: AuthService,
    private userService: UserService
  ) {}

  ngOnInit(): void {}

  @Input() activeUser: User | null = null;
  @Input() chats: Chat[] = [];

  selected: Chat | null = null;
  newChat: Chat = new Chat();
  editChat: Chat | null = null;
  chat: Chat | null = null;
  chatDetail: Chat | null = null;

  addButton: boolean = false;

  create(newChat: Chat, receiverId: number) {
    if (this.activeUser)
      this.chatService
        .create(newChat, this.activeUser.id, receiverId)
        .subscribe(
          (created) => {
            console.log('Chat created');
            console.log(created);
            this.newChat = new Chat();
            this.addButton = false;
            if (this.activeUser != null) this.loadChats(this.activeUser.id);
          },
          (fail) => {
            console.error('Something went wrong during chat creation', fail);
          }
        );
  }

  updateExp(editChat: Chat) {
    if (this.activeUser)
      this.chatService
        .update(editChat.id, this.activeUser.id, editChat)
        .subscribe(
          (updated) => {
            console.log('User Chat has updated successfully');
            this.editChat = null;
            if (this.activeUser) this.loadChats(this.activeUser.id);
          },
          (fail) => {
            console.error('Something went wrong with updating chat', fail);
          }
        );
  }

  loadChats(userId: number) {
    this.addButton = false;
    this.chatService.senderIndex(userId).subscribe(
      (data) => {
        this.chats = data;
        // this.editExp = null;
        // this.chatDetail = null;
      },
      (err) => {
        console.error('Error retrieving skill list', err);
        console.error(err);
      }
    );
  }

  deleteExp(chatId: number) {
    if (this.activeUser)
      this.chatService.destroy(chatId, this.activeUser.id).subscribe(
        (success) => {
          this.chatDetail = null;
          if (this.activeUser) this.loadChats(this.activeUser.id);
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
  setAddButton() {
    this.addButton = true;
  }
}
