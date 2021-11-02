import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Education } from 'src/app/models/education';
import { Experience } from 'src/app/models/experience';
import { JobApplication } from 'src/app/models/job-application';
import { JobPost } from 'src/app/models/job-post';
import { Skill } from 'src/app/models/skill';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit {

  constructor(private userService: UserService, private authService: AuthService, private router: Router) { }

  experiences: Experience[] = [];
  skills: Skill[] = [];
  educations: Education[] = [];
  applications: JobApplication[] =[];
  jobPosts: JobPost[] = [];
  selected: User | null = null;
  activeUser: User | null = null;
  loaded: boolean = false;

  ngOnInit(): void {
    if(this.loggedIn()){
      this.getActiveUser();
    }
  }

  loadProfileInfo(username: string){
    this.userService.show(username).subscribe(
      data => {
        this.skills = data.skills;
        this.educations = data.educations;
        this.experiences = data.experiences;
        this.applications = data.applications;
        this.jobPosts = data.posts;
        this.loaded = true;
      },
      err => {
        console.error(err);
      }
    );
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
          this.loadProfileInfo(this.activeUser.username);
        },
        err => {
          console.error(err);
        }
      );
    }
  }

  loggedIn(){
    return this.authService.checkLogin();
  }

editProfile(){
  this.router.navigateByUrl('editprofile');
}
  // selectDev(dev: Developer){
  //   this.selected = dev;
  //   this.loadProfileInfo(dev.id);
  // }
}
