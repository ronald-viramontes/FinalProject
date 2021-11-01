import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/models/client';
import { Developer } from 'src/app/models/developer';
import { JobApplication } from 'src/app/models/job-application';
import { JobPost } from 'src/app/models/job-post';
import { JobStatus } from 'src/app/models/job-status';
import { JobType } from 'src/app/models/job-type';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { JobPostService } from 'src/app/services/job-post.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-job-post',
  templateUrl: './job-post.component.html',
  styleUrls: ['./job-post.component.css'],
})
export class JobPostComponent implements OnInit {
  jobPosts: JobPost[] = [];
  jobStatuses: JobStatus[] = [];
  jobTypes: JobType[] = [];
  showNewJob: boolean = false;
  newJob: JobPost = new JobPost();
  selected: JobPost | null = null;
  editJob: JobPost | null = null;
  apps: JobApplication[] | null = null;
  activeUser: User | null = null;
  activeDev: Developer | null = null;
  activeClient: Client = new Client();

  constructor(
    private jobService: JobPostService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.reloadJobs();
    console.log(this.activeClient);

    if (this.loggedIn()) {
      this.getActiveUser();
    }
  }
  loggedIn() {
    return this.authService.checkLogin();
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

  getActiveUser() {
    let creds = this.authService.getCredentials();
    if (creds != null) {
      creds = atob(creds);
      console.log(creds);

      let unArr = creds.split(':');
      let username = unArr[0];
      this.userService.show(username).subscribe(
        (data) => {
          console.log(data);
          this.activeUser = data;
          this.activeDev = data.developer;
          this.activeClient = data.client;
          console.log(data.client);

        },
        (err) => {
          console.error(err);
        }
      );
    }
  }
  setEditJob(job: JobPost) {
    this.editJob = job;
  }
  displayJob(job: JobPost) {
    this.selected = job;
    this.apps = this.selected.applications;
    console.log(this.selected);
  }
  returnToList() {
    this.selected = null;
  }
  createNewPost() {
    this.showNewJob = true;
    this.newJob.client = this.activeClient;
    console.log(this.newJob);

    this.jobService.indexStatus().subscribe(
      (statusList) => {
        this.jobStatuses = statusList;
      },
      (fail) => {
        console.error('Job Status load failed');
      }
    );
    this.jobService.indexType().subscribe(
      (typeList)=> {
        this.jobTypes = typeList;
      },
      (fail)=>{
        console.error('Job Type load failed');
      }
    )
    console.log(this.activeClient);
    console.log(this.activeUser);
  }
  createPost(jobPost: JobPost) {
    console.log(jobPost);
    this.jobService.create(jobPost).subscribe(
      (created) => {
        console.log('Job Post Created');
      },
      (failed) => {
        console.error('Error creating Job Post');
      }
    );
  }
  setStatus(jobStatus: JobStatus) {
    this.newJob.status = jobStatus;
    console.log(this.newJob);
  }
  setType(jobType: JobType) {
    this.newJob.type = jobType;
    console.log(this.newJob);
  }
}
