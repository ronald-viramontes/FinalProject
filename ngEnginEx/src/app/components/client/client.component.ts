import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs/internal/Observable';
import { Client } from 'src/app/models/client';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css'],
})
export class ClientComponent implements OnInit {
  client: Client = new Client();
  user: User = new User(0, '', '', true, '');
  newClient: Client = new Client();
  selected: Client | null = null;
  editClient: Client | null = null;
  clients: Client[] = [];
  constructor(
    private clientService: ClientService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private auth: AuthService
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
    this.clientService.create(newClient).subscribe(
      (created) => {
        console.log('Client created');
        console.log(created);
        this.ngOnInit();
        this.reloadClients;
        this.newClient = new Client();
      },
      (fail) => {
        console.error('Something went wrong during client creation', fail);
      }
    );
  }

  updateClient(client: Client) {
    this.clientService.update(client.id, client).subscribe(
      (updated) => {
        this.client = updated;
        this.editClient = null;
        this.displayTable();
      },
      (fail) => {
        console.error('Something went wrong with updating client', fail);
      }
    );
  }

  reloadClients(): void {
    this.clientService.index().subscribe(
      (data) => {
        this.clients = data;
      },
      (err) => {
        console.error('Error retrieving client list', err);
        console.error(err);
      }
    );
  }

  deleteClient(clientId: number) {
    this.clientService.destroy(clientId).subscribe(
      (success) => {
        this.editClient = null;
        this.reloadClients();
        console.log('Successfully removed client', success);
      },
      (fail) => {
        console.error('Failed to remove user', fail);
      }
    );
  }

  displayClient(client: Client) {
    this.editClient = client;
  }

  displayTable() {
    this.reloadClients();
    return (this.editClient = null);
  }

  setEditClient() {
    this.editClient = Object.assign({}, this.selected);
  }

  register(user: User) {
    console.log(user);
    this.auth.register(user).subscribe(
      (data) => {
        console.log(data);
        this.user = new User();
        this.reloadClients();
      },
      (err) => {
        console.error('LoginComponent.login(): error registering');
        console.error(err);
      }
    );
  }
}
