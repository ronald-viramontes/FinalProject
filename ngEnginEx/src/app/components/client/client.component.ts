import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/models/client';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css'],
})
export class ClientComponent implements OnInit {
  client: Client | null = null;
  newClient: Client | null = null;
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
      this.newClient = null;
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
}
