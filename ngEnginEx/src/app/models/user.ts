import { Client } from "./client";
import { Developer } from "./developer";

export class User {

  id: number;
  username: string;
  password: string;
  enabled: boolean;
  role: string;
  developer: Developer;
  client: Client;

  constructor(id: number = 0, username: string ='', password: string='', enabled: boolean=true, role: string='', developer: Developer = new Developer(), client: Client = new Client()){
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.role = role;
    this.developer = developer;
    this.client = client;
  }
}
