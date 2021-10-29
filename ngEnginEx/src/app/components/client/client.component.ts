import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/models/client';
import { User } from 'src/app/models/user';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css'],
})
export class ClientComponent implements OnInit {
  client: Client | null = null;
  user: User = new User();
  newClient: Client = new Client();
  selected: Client | null = null;
  editSelected: Client | null = null;
  clients: Client[] = [];
  constructor(
    private clientService: ClientService,
    private currentRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    let id = this.currentRoute.snapshot.params['id'];
    if (this.currentRoute.snapshot.paramMap.get('id')) {
      this.clientService.show(id).subscribe(
        (found) => {
          this.selected = found;
        },
        (notFound) => {
          console.error('Client not found');
          console.error(notFound);
          this.router.navigateByUrl('**');
        }
      );
    } else {
      this.reloadClients();
    }
  }

  addClient(newClient: Client) {
    this.clientService.create(newClient).subscribe((created) => {
      console.log('Client created');
      console.log(created);
      this.reloadClients;
      this.newClient = new Client();
    });
  }

  updateClient(userId: number, clientId: number, client: Client) {
    this.clientService
      .update(userId, client.id, client)
      .subscribe((updated) => {
        this.client = updated;
      });
  }

  reloadClients(): void {
    this.clientService.index().subscribe(
      (data) => {
        this.clients = data;
      },
      (err) => {
        console.error('Error retrieving todo list', err);
        console.error(err);
      }
    );
  }

  deleteClient(userId: number, clientId: number) {
    this.clientService.destroy(userId, clientId).subscribe(
      (success) => {
        console.log('Successfully removed client', success);
      },
      (fail) => {
        console.error('Failed to remove user', fail);
      }
    );
    this.reloadClients();
  }
}
