import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JobApplication } from 'src/app/models/job-application';
import { JobPost } from 'src/app/models/job-post';
import { JobStatus } from 'src/app/models/job-status';
import { JobType } from 'src/app/models/job-type';
import { User } from 'src/app/models/user';
import { OpenJobPipe } from 'src/app/pipes/open-job.pipe';
import { UserJobPipe } from 'src/app/pipes/user-job.pipe';
import { AuthService } from 'src/app/services/auth.service';
import { JobPostService } from 'src/app/services/job-post.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-visitor-home',
  templateUrl: './visitor-home.component.html',
  styleUrls: ['./visitor-home.component.css'],
})
export class VisitorHomeComponent implements OnInit {
  ngOnInit(): void {
    this.loadStatusAndTypes();
    this.loadJobs();
  }

  constructor(
    private jobService: JobPostService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private userService: UserService,
    private jobPipe: UserJobPipe,
    private openJob: OpenJobPipe
  ) {}

  activeUser: User = new User();
  isOwner: boolean = false;

  selected: JobPost | null = null;

  jobPosts: JobPost[] = [];
  jobStatuses: JobStatus[] = [];
  jobTypes: JobType[] = [];

  apps: JobApplication[] = [];

  statusId: number = 1;

  displayJob(job: JobPost) {
    this.selected = job;
  }

  returnToList() {
    this.selected = null;
    this.loadJobs();
  }

  cancel() {
    this.router.navigateByUrl('/jobs');
  }

  loadStatusAndTypes() {
    this.jobService.visitorStatus().subscribe(
      (statusList) => {
        this.jobStatuses = statusList;
      },
      (fail) => {
        console.error('Job Status load failed');
      }
    );
    this.jobService.visitorTypes().subscribe(
      (typeList) => {
        this.jobTypes = typeList;
      },
      (fail) => {
        console.error('Job Type load failed');
      }
    );
  }

  loadJobs(): void {
    this.jobService.visitorIndex().subscribe(
      (jobList) => {
        this.selected = null;
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
      let unArr = creds.split(':');
      let username = unArr[0];
      this.userService.show(username).subscribe(
        (data) => {
          this.activeUser = data;
        },
        (err) => {
          console.error(err);
        }
      );
    }
  }

  loggedIn() {
    return this.authService.checkLogin();
  }
}
