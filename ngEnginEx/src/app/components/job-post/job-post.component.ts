import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
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
  template: `<ng-template #content let-modal id="editMyJob"></ng-template>`,
})
export class JobPostComponent implements OnInit {
  ngOnInit(): void {
    this.getActiveUser();
    this.loadStatusAndTypes();
    this.loadJobs();
  }

  @ViewChild('content', { static: true })
  content!: TemplateRef<any>;

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
    private modalService: NgbModal,
    private jobService: JobPostService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private userService: UserService,
    private jobPipe: UserJobPipe,
    private openJob: OpenJobPipe,
    private appService: JobApplicationService
  ) {}

  @Input()
  activeUser: User | null = null;
  isOwner: boolean = false;

  selected: JobPost | null = null;
  editJob: JobPost | null = null;

  jobPosts: JobPost[] = [];
  jobStatuses: JobStatus[] = [];
  jobTypes: JobType[] = [];

  closeResult: string = '';
  reason: string = '';

  apps: JobApplication[] = [];
  editApp: JobApplication | null = null;

  newAppCount: number = 0;
  // showButton: boolean = false;

  statusId: number = 1;

  displayJob(job: JobPost) {
    this.selected = job;
    this.apps = this.selected.applications;
    console.log('log in display job', this.apps);
    if (this.activeUser == this.selected.user) this.isOwner = true;
  }

  setEditJob(job: JobPost) {
    this.editJob = job;
    if (this.activeUser) this.modalService.open(this.content);
  }

  returnToList() {
    this.selected = null;
    this.isOwner = false;
    this.editJob = null;
    this.loadJobs();
  }

  cancel() {
    this.editJob = null;
    this.router.navigateByUrl('/jobs');
  }

  loadStatusAndTypes() {
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

  updateMyPost(editJob: JobPost) {
    this.jobService.editPost(editJob).subscribe(
      (updated) => {
        this.editJob = null;
        this.loadJobs();
      },
      (err) => {
        console.error(
          'Something went wrong during update in updateMyPost',
          err
        );
      }
    );
  }

  // getRoute() {
  //   return this.router.url === '/home';
  // }

  deletePost(post: JobPost) {
    if (confirm('Are you sure you want to delete this Job Posting?')) {
      this.jobService.delete(post).subscribe(
        (data) => {
          this.editJob = null;
          this.loadJobs();
        },
        (err) => {
          console.error(err);
        }
      );
    }
  }

  applyForJob(job: JobPost) {
    if (this.activeUser)
      if (this.selected)
        this.appService.newJobApplication(this.selected).subscribe(
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

  approveApp(app: JobApplication) {
    this.appService.approveApp(app.id, app).subscribe(
      (updated) => {
        console.log('Application has been approved');
        console.log(updated);

        this.editApp = null;
        this.selected = null;
        this.loadJobs();
      },
      (fail) => {
        console.error('Application approval failed');
      }
    );
  }

  denyApp(app: JobApplication) {
    this.appService.deniedApp(app.id, app).subscribe(
      (updated) => {
        console.log('Application has been denied');
        this.editApp = null;
        this.selected = null;
        this.loadJobs();
      },
      (fail) => {
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
      (fail) => {
        console.error('Job posts load failed');
      }
    );
  }

  open(content: any) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        }
      );
    this.editJob = null;
    this.loadJobs();
  }

  getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
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
