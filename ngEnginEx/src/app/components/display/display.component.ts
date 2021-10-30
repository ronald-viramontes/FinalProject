import { Component, OnInit } from '@angular/core';
import { Education } from 'src/app/models/education';
import { Experience } from 'src/app/models/experience';
import { Skill } from 'src/app/models/skill';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ClientService } from 'src/app/services/client.service';
import { DeveloperService } from 'src/app/services/developer.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit {

  constructor(private developerService: DeveloperService, private clientService: ClientService, private userService: UserService, private authService: AuthService) { }

  experiences: Experience[] = [];
  skills: Skill[] =[];
  educations: Education[] = [];
  selected: number | null = null;
  // unArr: string[] = [];
  // creds: string | null = null;
  // username: string | null = null;
  activeUser: User | null = null;

  ngOnInit(): void {
    this.getActiveUser();
  }

  loadProfileInfo(selected: number){
    this.developerService.show(selected).subscribe(
      data => {
        this.skills = data.skills;
        this.educations = data.educations;
        this.experiences = data.experiences;
      },
      err => {
        console.error(err);
      }
    )
  }


  getActiveUser(){
    let creds = this.authService.getCredentials();
    if(creds != null){
      creds = atob(creds);
      let unArr = creds.split(':');
      let username = unArr[0]
      this.userService.show(username).subscribe(
        data => {
          this.activeUser = data;
        },
        err => {
          console.error(err);
        }
      );
    }
  }

}
