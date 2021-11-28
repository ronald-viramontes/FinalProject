import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Chat } from 'src/app/models/chat';
import { Education } from 'src/app/models/education';
import { Experience } from 'src/app/models/experience';
import { JobApplication } from 'src/app/models/job-application';
import { JobApplicationComment } from 'src/app/models/job-application-comment';
import { JobPost } from 'src/app/models/job-post';
import { JobStatus } from 'src/app/models/job-status';
import { JobType } from 'src/app/models/job-type';
import { Skill } from 'src/app/models/skill';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { JobApplicationService } from 'src/app/services/job-application.service';
import { JobPostService } from 'src/app/services/job-post.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-my-job-posts',
  templateUrl: './my-job-posts.component.html',
  styleUrls: ['./my-job-posts.component.css'],
})
export class MyJobPostsComponent implements OnInit {
  constructor(
    private jobSvc: JobPostService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private authService: AuthService,
    private userService: UserService,
    private modalService: NgbModal,
    private appService: JobApplicationService
  ) {}

  @Input() recMsg: Chat[] = [];
  @Input() sentMsg: Chat[] = [];
  @Input() userSkills: Skill[] = [];
  @Input() userEdu: Education[] = [];
  @Input() userApps: JobApplication[] = [];
  @Input() userExps: Experience[] = [];
  @Input() userPosts: JobPost[] = [];
  @Input() active: User | null = null;

  posts: JobPost[] = [];
  closeResult: string = '';
  userJobs: JobPost[] = [];
  user: User | null = null;
  refresh: boolean = false;

  jobs: JobPost[] = [];

  userJob: JobPost = new JobPost();
  postId: JobPost = new JobPost();

  newPost: JobPost = new JobPost();
  newApp: JobApplication = new JobApplication();
  newComments: JobApplicationComment[] = [];

  // approvedApp: JobApplication = new JobApplication(0, false, 'Approved', '');
  // deniedApp: JobApplication = new JobApplication(0, true, 'Approved', '');

  editApp: JobApplication | null = null;
  editJob: JobPost | null = null;

  apps: JobApplication[] = [];
  selected: JobPost | null = null;

  jobStatuses: JobStatus[] = [];
  jobTypes: JobType[] = [];

  refreshJob: JobPost | null = null;

  tempJob: JobPost | null = null;
  openForm: boolean = false;
  ngOnInit(): void {
    this.loadStatusAndTypes();
    this.reloadMyJobs();
    this.selectJobs();
  }

  selectJob(job: JobPost) {
    if (this.selected) {
      this.selected = null;
      this.apps = [];
    }
    this.selected = job;

    this.apps = this.selected.applications;
  }
  formOpen() {
    this.openForm = true;
  }

  formClose() {
    this.openForm = false;
  }

  // deselectNewJob(job: JobPost) {
  //   this.refreshJob = job;
  // }

  selectJobs() {
    this.jobs = this.posts;
    this.refreshJob = null;
    this.newPost = new JobPost();
  }

  setEditJob(job: JobPost) {
    this.editJob = job;
  }

  selectApp(appId: number) {
    this.jobSvc.appById(appId).subscribe(
      (data) => {},
      (fail) => {
        console.error('Job Status load failed');
      }
    );
  }

  reloadMyJobs(): void {
    if (this.active)
      this.jobSvc.postsByUser(this.active.id).subscribe(
        (jobs) => {
          this.posts = jobs;
          this.selected = null;
          this.editJob = null;
          this.refreshJob = null;
          this.selectJobs();
        },
        (fail) => {
          console.error('Job posts load failed');
        }
      );
  }

  loadStatusAndTypes() {
    this.jobSvc.indexStatus().subscribe(
      (statusList) => {
        this.jobStatuses = statusList;
      },
      (fail) => {
        console.error('Job Status load failed');
      }
    );
    this.jobSvc.indexType().subscribe(
      (typeList) => {
        this.jobTypes = typeList;
      },
      (fail) => {
        console.error('Job Type load failed');
      }
    );
  }

  submitApplication(jobPost: JobPost) {
    this.newApp.id = 0;
    this.newApp.approved = false;
    this.newApp.status = 'Awaiting Decision';
    this.newApp.date = '';
    this.newApp.jobPost = jobPost;
    if (this.active) this.newApp.user = this.active;
    this.newApp.comments = this.newComments;
    this.jobSvc.createApp(this.newApp).subscribe(
      (created) => {
        console.log('Job App Created');
        this.reloadMyJobs();
        this.newApp = new JobApplication();
      },
      (failed) => {
        console.error('Error creating Job Post', failed);
      }
    );
  }

  createAPost(post: JobPost) {
    this.jobSvc.createPost(post).subscribe(
      (created) => {
        console.log('Job Post Created');
        this.newPost = new JobPost();
        this.reloadMyJobs();
      },
      (failed) => {
        console.error('Error creating Job Post', failed);
      }
    );
  }

  updateMyPost(editJob: JobPost) {
    this.jobSvc.editPost(editJob).subscribe(
      (updated) => {
        this.editJob = null;
        this.reloadMyJobs();
      },
      (err) => {
        console.error(
          'Something went wrong during update in updateMyPost',
          err
        );
      }
    );
  }
  deleteMyPost(post: JobPost) {
    if (confirm('Are you sure you want to delete this Job Posting?')) {
      this.jobSvc.delete(post).subscribe(
        (data) => {
          this.editJob = null;
          this.reloadMyJobs();
        },
        (err) => {
          console.error(err);
        }
      );
    }
  }
  postById(post: JobPost) {
    // post = this.postId;
    this.jobSvc.postById(post).subscribe(
      (updated) => {
        console.log('approval happening!');
        console.log(updated);

        this.tempJob = updated;
      },
      (fail) => {
        console.error('This approval failed');
      }
    );
  }
  approveApp(app: JobApplication) {
    this.postId = app.jobPost;

    console.log('This is jobpostID', this.postId);
    console.log('This is jobpostID', app.jobPost);
    console.log('This is in approveApp', app);
    this.appService.approveApp(app.id, app).subscribe(
      (updated) => {
        console.log('Application has been approved');
        console.log(updated);

        this.editApp = null;
        this.selected = null;
        this.postId = new JobPost();
        this.reloadMyJobs();
      },
      (fail) => {
        console.error('Application approval failed');
      }
    );
  }

  denyApp(app: JobApplication) {
    this.postId = app.jobPost;

    this.appService.deniedApp(app.id, app).subscribe(
      (updated) => {
        console.log('Application has been denied');
        this.editApp = null;
        this.selected = null;
        this.postId = new JobPost();
        this.reloadMyJobs();
      },
      (fail) => {
        console.log('Failed during application rejection.');
      }
    );
  }

  open(content: any, _openForm = true) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
          this.newPost = new JobPost();
          this.reloadMyJobs();
        }
      );
  }

  private getDismissReason(reason: any, _openForm = false): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  loggedIn() {
    return this.authService.checkLogin();
  }
}
