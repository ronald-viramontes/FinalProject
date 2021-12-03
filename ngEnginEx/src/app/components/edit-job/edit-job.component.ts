import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { JobPost } from 'src/app/models/job-post';
import { JobStatus } from 'src/app/models/job-status';
import { JobType } from 'src/app/models/job-type';
import { AuthService } from 'src/app/services/auth.service';
import { JobPostService } from 'src/app/services/job-post.service';
import { UserService } from 'src/app/services/user.service';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-edit-job',
  templateUrl: './edit-job.component.html',
  styleUrls: ['./edit-job.component.css'],
})
export class EditJobComponent implements OnInit {
  @Input() editJob: JobPost | null = null;

  @ViewChild('editJob') updateJobForm = NgForm;

  @ViewChild('updateJob', { static: true })
  updateJob!: TemplateRef<any>;

  constructor(
    private jobService: JobPostService,
    private modalService: NgbModal,
    private router: Router,
    private authService: AuthService,
    private userService: UserService
  ) {}

  closeResult: string = '';

  jobStatuses: JobStatus[] = [];
  jobTypes: JobType[] = [];
  editPost: JobPost | null = null;
  openForm: boolean = false;

  ngOnInit(): void {
    this.loadStatusAndTypes();
    this.modalService.open(this.editJob);
  }

  cancel() {
    this.editPost = new JobPost();
    this.editJob = new JobPost();
    this.router.navigateByUrl('/home');
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

  onUpdateMyPost(editJob: JobPost) {
    this.jobService.editPost(editJob).subscribe(
      (updated) => {
        this.router.navigateByUrl('/home');
        this.editJob = new JobPost();
      },
      (err) => {
        console.error(
          'Something went wrong during update in updateMyPost',
          err
        );
        this.router.navigateByUrl('/home');
      }
    );
  }

  deleteMyPost(post: JobPost) {
    if (confirm('Are you sure you want to delete this Job Posting?')) {
      this.jobService.delete(post).subscribe(
        (data) => {
          this.editPost = new JobPost();
          this.router.navigateByUrl('/home');
        },
        (err) => {
          this.router.navigateByUrl('/home');
          console.error(err);
        }
      );
    }
  }

  open(content: any) {
    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (result) => {
          this.router.navigateByUrl('/home');
          this.closeResult = `Closed with: ${result}`;
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
          this.router.navigateByUrl('/home');
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
