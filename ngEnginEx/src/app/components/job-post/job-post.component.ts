import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JobPost } from 'src/app/models/job-post';
import { JobPostService } from 'src/app/services/job-post.service';

@Component({
  selector: 'app-job-post',
  templateUrl: './job-post.component.html',
  styleUrls: ['./job-post.component.css']
})
export class JobPostComponent implements OnInit {

  jobPosts: JobPost[] = [];
  //showList = true;
  newJob: JobPost = new JobPost();
  selected: JobPost | null = null;
  editJob: JobPost | null = null;


  constructor(
    private jobService: JobPostService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.reloadJobs();
  }
  reloadJobs(): void {
    this.jobService.index().subscribe(
      (jobList) => {
        this.jobPosts = jobList;
      },
      (fail) => {
        console.error('Job posts load failed');
      }
    );
  }
  displayJob(job: JobPost) {
    this.selected = job;
    console.log(this.selected);
  }
  returnToList() {
    this.selected = null;
  }
}
