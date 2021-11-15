import { Component, Input, OnInit } from '@angular/core';
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
  selector: 'app-job-post',
  templateUrl: './job-post.component.html',
  styleUrls: ['./job-post.component.css'],
})
export class JobPostComponent implements OnInit {
  @Input() jobPosts: JobPost[] = [];
  @Input() activeUser: User | null = null;
  jobStatuses: JobStatus[] = [];
  jobTypes: JobType[] = [];
  showNewJob: boolean = false;
  isOwner: boolean = false;
  newPost: JobPost = new JobPost();
  newJob: JobPost = new JobPost(
    0,
    '',
    '',
    '',
    0,
    true,
    '',
    '',
    new JobType(1),
    new User(),
    new JobStatus(2)
  );
  selected: JobPost | null = null;
  editJob: JobPost | null = null;
  apps: JobApplication[] | null = null;
  editApp: JobApplication | null = null;
  // activeUser: User = new User();
  currentDate: Date = new Date();
  newJobApp: JobApplication = new JobApplication();
  appDetail: boolean = false;
  statusId: number = 1;
  newAppCount: number = 0;
  showButton: boolean = false;

  appStyleBadge(jobPost: JobPost): string {
    let count = 0;
    if (jobPost.applications.length > 0) {
      for (let app of jobPost.applications) {
        if (app.status === 'Open') {
          count++;
        }
      }
      if (count > 0) {
        this.newAppCount = count;
        count = 0;
        return 'badge rounded-pill bg-danger';
      }
    }
    return 'appWaitingBadge';
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

  ngOnInit(): void {
    if (this.loggedIn()) {
      // this.getActiveUser();
    }
    this.jobService.indexStatus().subscribe(
      (statusList) => {
        this.jobStatuses = statusList;
      },
      (fail) => {
        console.error('Job Status load failed');
      }
    );
    this.reloadJobs();
    this.getRoute();
    let passedPost: JobPost = history.state;
    if (passedPost.id !== undefined) {
      this.displayJob(passedPost);
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

  // getActiveUser() {
  //   let creds = this.authService.getCredentials();
  //   if (creds != null) {
  //     creds = atob(creds);
  //     let unArr = creds.split(':');
  //     let username = unArr[0];
  //     this.userService.show(username).subscribe(
  //       (data) => {
  //         console.log(data);
  //         this.activeUser = data;
  //       },
  //       (err) => {
  //         console.error(err);
  //       }
  //     );
  //   }
  // }

  setEditJob(job: JobPost) {
    this.editJob = job;
    this.jobService.indexStatus().subscribe(
      (statusList) => {
        this.jobStatuses = statusList;
      },
      (fail) => {
        console.error('Job Status load failed');
      }
    );
    this.jobService.indexType().subscribe(
      (typeList) => {
        this.jobTypes = typeList;
      },
      (fail) => {
        console.error('Job Type load failed');
      }
    );
  }

  displayJob(job: JobPost) {
    this.selected = job;
    this.apps = this.selected.applications;
    if (this.activeUser && this.selected.user) {
      if (this.selected.user.id != this.activeUser.id) {
        this.isOwner = true;
      }
    }
  }

  returnToList() {
    this.selected = null;
    this.isOwner = false;
    this.reloadJobs();
  }

  createNewPost() {
    console.log(this.showNewJob);

    if (this.activeUser) {
      this.newJob.user = this.activeUser;
    }
    console.log(this.newJob);
    console.log(this.activeUser);

    this.jobService.indexStatus().subscribe(
      (statusList) => {
        this.jobStatuses = statusList;
      },
      (fail) => {
        console.error('Job Status load failed');
      }
    );
    this.jobService.indexType().subscribe(
      (typeList) => {
        this.jobTypes = typeList;
      },
      (fail) => {
        console.error('Job Type load failed');
      }
    );
  }

  createPreCall(jobPost: JobPost) {
    this.createPost(jobPost);
    this.reloadJobs();
  }

  createPost(jobPost: JobPost) {
    // console.log(jobPost);
    jobPost.user = new User();
    if (this.activeUser) {
      console.log(this.activeUser.id);

      jobPost.user.id = this.activeUser.id;
    }
    this.jobService.create(jobPost).subscribe(
      (created) => {
        console.log('Job Post Created');
        this.reloadJobs();
        this.newJob = new JobPost(
          0,
          '',
          '',
          '',
          0,
          true,
          '',
          '',
          new JobType(1),
          new User(),
          new JobStatus(2)
        );
        this.showNewJob = false;
      },
      (failed) => {
        console.error('Error creating Job Post');
      }
    );
  }
  createJobPost(newPost: JobPost) {
    // console.log(jobPost);
    if (this.activeUser)
      this.jobService.createPost(newPost, this.activeUser.id).subscribe(
        (created) => {
          console.log('Job Post Created');
          this.reloadJobs();
          this.newPost = new JobPost();
        },
        (failed) => {
          console.error('Error creating Job Post');
        }
      );
  }

  createApplication(jobPost: JobPost) {
    if (this.activeUser) {
      this.newJobApp.user = this.activeUser;
    }
    console.log(this.activeUser);

    if (this.newJobApp.user) this.newJobApp.user.posts = [];
    this.newJobApp.detail = null;
    console.log(this.newJobApp);
    if (this.activeUser)
      this.jobService
        .createApplication(jobPost.id, this.activeUser.id)
        .subscribe(
          (created) => {
            console.log('Job App created successfully');
            this.selected = null;
            this.reloadJobs();
          },
          (failed) => {
            console.error('Error creation Job App');
          }
        );
    console.log('Done');
    //this.
  }

  approveApplication(app: JobApplication) {
    console.log('in approve');
    console.log(app);
    app.user = new User();
    if (app) {
      app.status = 'Approved';
      this.jobService.approveApplication(app, 1).subscribe(
        (udpated) => {
          console.log('approval happening!');
          this.reloadJobs();
          this.editApp = null;
          this.selected = null;
        },
        (fail) => {
          console.error('This approval failed');
        }
      );
    } else {
      console.log('No edit app...');
    }
  }

  declineApplication(app: JobApplication) {
    if (app) {
      app.status = 'Declined';
      this.jobService.approveApplication(app, 0).subscribe(
        (updated) => {
          this.editApp = null;
          this.selected = null;
        },
        (fail) => {
          console.error('This decline did not work.');
        }
      );
    }
  }
  setStatus(jobStatus: JobStatus) {
    this.newJob.status = jobStatus;
    console.log(this.newJob);
  }
  setType(jobType: JobType) {
    this.newJob.type = jobType;
    console.log(this.newJob);
  }

  setEditStatus(jobStatus: JobStatus) {
    if (this.editJob) this.editJob.status = jobStatus;
    console.log(this.newJob);
  }
  setEditType(jobType: JobType) {
    if (this.editJob) this.editJob.type = jobType;
    console.log(this.newJob);
  }

  update(jobPost: JobPost) {
    console.log(jobPost);
    jobPost.applications = [];
    return this.jobService.update(jobPost).subscribe((updated) => {
      this.reloadJobs();
      this.editJob = null;
    });
  }

  getRoute() {
    return this.router.url === '/home';
  }

  deletePost(post: JobPost) {
    if (confirm('Are you sure you want to delete this Job Posting?')) {
      this.jobService.delete(post).subscribe(
        (data) => {
          this.editJob = null;
          this.reloadJobs();
        },
        (err) => {
          console.error(err);
        }
      );
    }
  }

  scrollStyle() {
    if (this.getRoute()) {
      return 'scrollable';
    } else {
      return '';
    }
  }
  formShow() {
    this.showButton = false;
  }
  formHide() {
    this.showButton = true;
  }
}
