import { Component, OnInit,Input } from '@angular/core';
import { JobApplication } from 'src/app/models/job-application';

@Component({
  selector: 'app-job-application',
  templateUrl: './job-application.component.html',
  styleUrls: ['./job-application.component.css']
})
export class JobApplicationComponent implements OnInit {

  constructor() { }

  @Input() applications: JobApplication[] =[];

  ngOnInit(): void {
  }

}
