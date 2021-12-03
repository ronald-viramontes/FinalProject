import { Component, Input, OnInit, Output } from '@angular/core';
import { JobApplication } from 'src/app/models/job-application';
import { JobPost } from 'src/app/models/job-post';
import { JobStatus } from 'src/app/models/job-status';
import { JobType } from 'src/app/models/job-type';
import { User } from 'src/app/models/user';
import { OpenJobPipe } from 'src/app/pipes/open-job.pipe';
import { UserJobPipe } from 'src/app/pipes/user-job.pipe';
import { AuthService } from 'src/app/services/auth.service';
import { JobApplicationService } from 'src/app/services/job-application.service';
import { JobPostService } from 'src/app/services/job-post.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-job-post',
  templateUrl: './job-post.component.html',
  styleUrls: ['./job-post.component.css'],
})
export class JobPostComponent implements OnInit {
  ngOnInit(): void {
    this.getActiveUser();
    this.loadStatusAndTypes();
    this.loadJobs();
  }

  // appStyleBadge(jobPost: JobPost): string {
  //   let count = 0;

  //   if (jobPost.applications.length > 0) {
  //     for (let app of jobPost.applications) {
  //       if (app.status === 'Open') {
  //         count++;
  //       }
  //     }
  //     if (count > 0) {
  //       this.newAppCount = count;
  //       count = 0;
  //       return 'badge rounded-pill bg-danger';
  //     }
  //   }
  //   return 'appWaitingBadge';
  // }

  constructor(
    private jobService: JobPostService,
    private authService: AuthService,
    private userService: UserService,
    private appService: JobApplicationService
  ) {}

  @Input()
  activeUser: User | null = null;

  isOwner: boolean = false;

  selected: JobPost | null = null;

  jobPosts: JobPost[] = [];
  jobStatuses: JobStatus[] = [];
  jobTypes: JobType[] = [];

  closeResult: string = '';
  reason: string = '';

  apps: JobApplication[] = [];
  editApp: JobApplication | null = null;

  openForm: boolean = false;

  newAppCount: number = 0;

  statusId: number = 1;

  displayJob(job: JobPost) {
    this.selected = job;
    this.apps = this.selected.applications;
    console.log('log in display job', this.apps);
    if (this.activeUser == this.selected.user) this.isOwner = true;
  }

  returnToList() {
    this.selected = null;
    this.loadJobs();
  }

  loadStatusAndTypes() {
    this.jobService.indexStatus().subscribe(
      (statusList) => {
        this.jobStatuses = statusList;
      },
      () => {
        console.error('Job Status load failed');
      }
    );
    this.jobService.indexType().subscribe(
      (typeList) => {
        this.jobTypes = typeList;
      },
      () => {
        console.error('Job Type load failed');
      }
    );
  }

  applyForJob() {
    if (this.activeUser)
      if (this.selected)
        this.jobService
          .createApplication(this.selected.id, this.activeUser.id)
          .subscribe(
            (updated) => {
              console.log('Application has been submitted', updated);

              this.selected = null;
              this.loadJobs();
            },
            (fail) => {
              console.error('Application approval submission failed', fail);
            }
          );
  }
  // applyForJob(job: JobPost) {
  //   if (this.activeUser)
  //     if (this.selected)
  //       this.appService.newJobApplication(this.selected).subscribe(
  //         (updated) => {
  //           console.log('Application has been submitted', updated);

  //           this.selected = null;
  //           this.loadJobs();
  //         },
  //         (fail) => {
  //           console.error('Application approval submission failed', fail);
  //         }
  //       );
  // }

  approveApp(app: JobApplication) {
    this.appService.approveApp(app.id, app).subscribe(
      (updated) => {
        console.log('Application has been approved');
        console.log(updated);

        this.editApp = null;
        this.selected = null;
        this.loadJobs();
      },
      () => {
        console.error('Application approval failed');
      }
    );
  }

  denyApp(app: JobApplication) {
    this.appService.deniedApp(app.id, app).subscribe(
      () => {
        console.log('Application has been denied');
        this.editApp = null;
        this.selected = null;
        this.loadJobs();
      },
      () => {
        console.log('Failed during application rejection.');
      }
    );
  }

  loadJobs(): void {
    this.jobService.index().subscribe(
      (jobList) => {
        this.selected = null;
        this.jobPosts = jobList;
      },
      () => {
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
