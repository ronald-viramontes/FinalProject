import { Component, Input, OnInit } from '@angular/core';
import { Chat } from 'src/app/models/chat';
import { User } from 'src/app/models/user';
import { ChatService } from 'src/app/services/chat.service';
import { ChatComponent } from '../chat/chat.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  constructor(private chatService: ChatService) {}
  @Input() activeUser: User | null = null;
  @Input() receivedChats: Chat[] = [];
  @Input() sentChats: Chat[] = [];
  ngOnInit() {
    this.loadReceivedChats();
  }
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
