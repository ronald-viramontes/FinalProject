import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { JobPost } from 'src/app/models/job-post';
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
  @Input() activeUser: User | null = null;
  @Input() jobPosts: JobPost[] = [];
  closeResult: string = '';

  ngOnInit(): void {
    super.ngOnInit();
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
