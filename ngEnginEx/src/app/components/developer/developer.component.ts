import { Component, OnInit } from '@angular/core';
import { Developer } from 'src/app/models/developer';
import { DeveloperService } from 'src/app/services/developer.service';

@Component({
  selector: 'app-developer',
  templateUrl: './developer.component.html',
  styleUrls: ['./developer.component.css']
})
export class DeveloperComponent implements OnInit {

  developers: Developer[] = [];

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
}
