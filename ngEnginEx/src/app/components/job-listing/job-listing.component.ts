import { HttpClient } from '@angular/common/http';
import {
  Component,
  Input,
  OnInit,
  ViewChild,
  TemplateRef,
} from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { JobPost } from 'src/app/models/job-post';
import { JobStatus } from 'src/app/models/job-status';
import { JobType } from 'src/app/models/job-type';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { JobPostService } from 'src/app/services/job-post.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-job-listing',
  templateUrl: './job-listing.component.html',
  styleUrls: ['./job-listing.component.css'],
  template: `<ng-template #content let-modal id="content"></ng-template>`,
})
export class JobListingComponent implements OnInit {
  closeResult: string = '';

  constructor(
    private jobSvc: JobPostService,
    private http: HttpClient,
    private modalService: NgbModal,
    private authService: AuthService,
    private userService: UserService,
    private router: Router
  ) {}

  @Input() active: User = new User();
  newPost: JobPost = new JobPost();
  jobStatuses: JobStatus[] = [];
  jobTypes: JobType[] = [];

  @ViewChild('content', { static: true })
  content!: TemplateRef<any>;

  ngOnInit(): void {
    this.loadStatusAndTypes();
    this.getActiveUser();
    if (this.active) this.modalService.open(this.content);
  }

  getActiveUser() {
    let creds = this.authService.getCredentials();
    if (creds != null) {
      creds = atob(creds);
      let unArr = creds.split(':');
      let username = unArr[0];
      this.userService.show(username).subscribe(
        (data) => {
          this.active = data;
        },
        (err) => {
          console.error(err);
        }
      );
    }
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

  cancel() {
    this.newPost = new JobPost();
    this.router.navigateByUrl('/home');
  }

  createAPost(post: JobPost) {
    this.jobSvc.createPost(post).subscribe(
      (created) => {
        console.log('Job Post Created');
        this.newPost = new JobPost();
        alert('Job Posted');
        this.router.navigateByUrl('/home');
      },
      (failed) => {
        console.error('Error creating Job Post', failed);
      }
    );
  }

  open(content: any) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
          this.router.navigateByUrl('/home');
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
          this.newPost = new JobPost();
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

  loggedIn() {
    return this.authService.checkLogin();
  }
}
