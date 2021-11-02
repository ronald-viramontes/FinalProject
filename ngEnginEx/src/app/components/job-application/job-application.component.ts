import { Component, OnInit,Input } from '@angular/core';
import { Router } from '@angular/router';
import { JobApplication } from 'src/app/models/job-application';

@Component({
  selector: 'app-job-application',
  templateUrl: './job-application.component.html',
  styleUrls: ['./job-application.component.css']
})
export class JobApplicationComponent implements OnInit {

  constructor(private router: Router) { }

  @Input() applications: JobApplication[] =[];

  ngOnInit(): void {
  }

  goToJobs(){
    this.router.navigateByUrl('jobs');
  }
}
