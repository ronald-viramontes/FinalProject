import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Chat } from 'src/app/models/chat';
import { Education } from 'src/app/models/education';
import { Experience } from 'src/app/models/experience';
import { JobApplication } from 'src/app/models/job-application';
import { JobPost } from 'src/app/models/job-post';
import { JobStatus } from 'src/app/models/job-status';
import { JobType } from 'src/app/models/job-type';
import { Skill } from 'src/app/models/skill';
import { User } from 'src/app/models/user';
import { OpenJobPipe } from 'src/app/pipes/open-job.pipe';
import { UserJobPipe } from 'src/app/pipes/user-job.pipe';
import { AuthService } from 'src/app/services/auth.service';
import { JobPostService } from 'src/app/services/job-post.service';
import { UserService } from 'src/app/services/user.service';
import { JobPostComponent } from '../job-post/job-post.component';

@Component({
  selector: 'app-my-job-posts',
  templateUrl: './my-job-posts.component.html',
  styleUrls: ['./my-job-posts.component.css'],
})
export class MyJobPostsComponent extends JobPostComponent implements OnInit {
  constructor(
    private jobSvc: JobPostService,
    jobService: JobPostService,
    route: ActivatedRoute,
    router: Router,
    authService: AuthService,
    userService: UserService,
    jobPipe: UserJobPipe,
    openJob: OpenJobPipe,
    private modalService: NgbModal
  ) {
    super(
      jobService,
      route,
      router,
      authService,
      userService,
      jobPipe,
      openJob
    );
  }

  @Input() recMsg: Chat[] = [];
  @Input() sentMsg: Chat[] = [];
  @Input() userSkills: Skill[] = [];
  @Input() userEdu: Education[] = [];
  @Input() userApps: JobApplication[] = [];
  @Input() userExps: Experience[] = [];
  @Input() userPosts: JobPost[] = [];
  @Input() active: User | null = null;
  user: User | null = null;

  jobs: JobPost[] = [];

  closeResult: string = '';
  apps: JobApplication[] = [];
  selected: JobPost | null = null;
  jobStatuses: JobStatus[] = [];
  jobTypes: JobType[] = [];

  ngOnInit(): void {
    // super.ngOnInit();
    if (this.active) console.log('activeUser Inint', this.active.posts.length);
    console.log('this.jobPost Ininit userExps', this.userExps.length);
    console.log('this.jobPost Ininit userSkills', this.userSkills.length);
    console.log('this.jobPost Ininit userSkills', this.userSkills.length);
    console.log('this.jobPost Ininit userApps', this.userApps.length);
    console.log('this.jobPost Ininit recMsg', this.recMsg.length);
    console.log('this.jobPost IninitsentMsg', this.sentMsg.length);
    console.log('this.jobPost Ininit', this.userEdu.length);
    this.loadStatusAndTypes();
    this.selectJobs();
    console.log('onInit jobTypes jobStatuses', this.jobStatuses.length);
    console.log('onInit jobTypes jobStatuses', this.jobTypes.length);
  }

  selectJob(job: JobPost) {
    this.selected = job;
    this.apps = this.selected.applications;
  }

  selectJobs() {
    this.jobs = this.userPosts;
    console.log('selectJobs() myJobPost', this.userPosts.length);
    console.log('selectJobs() myJobPost', this.jobs.length);
  }
  selectApp(appId: number) {
    this.jobSvc.appById(appId).subscribe(
      (data) => {},
      (fail) => {
        console.error('Job Status load failed');
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

  editMyJob(job: JobPost) {
    this.editJob = job;
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
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
}
