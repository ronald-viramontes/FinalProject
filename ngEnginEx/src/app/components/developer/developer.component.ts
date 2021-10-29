import { Component, OnInit } from '@angular/core';
import { Developer } from 'src/app/models/developer';
import { User } from 'src/app/models/user';
import { DeveloperService } from 'src/app/services/developer.service';

@Component({
  selector: 'app-developer',
  templateUrl: './developer.component.html',
  styleUrls: ['./developer.component.css']
})
export class DeveloperComponent implements OnInit {

  developers: Developer[] = [];
  selected: Developer | null = null;
  user: User = new User();
  dev: Developer = new Developer();

  constructor(private devSvc: DeveloperService) { }

  ngOnInit(): void {
    this.loadDevelopers();
  }


  loadDevelopers(): void {
    this.devSvc.index().subscribe(
      data => {
        this.developers = data;
      },
      err => {
        console.error('Error retrieving developers');
        console.error(err);
      }
    )
  }

  selectDev(devId: number){
    this.devSvc.show(devId).subscribe(
      data => {
        this.selected = data;
      },
      err => {
        console.error(err);
        console.error('Error retrieving developer');
      }
    )
  }

  // deleteDev(devId: number){
  //   this.devSvc.delete(devId).subscribe(
  //     data => {
  //       console.log(data);

  //     },
  //     err => {
  //       console.error(err);
  //       console.error('Error deleting developer');

  //     }
  //   )
  // }
}
