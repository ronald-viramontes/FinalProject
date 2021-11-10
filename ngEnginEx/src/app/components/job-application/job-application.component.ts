import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { JobApplication } from 'src/app/models/job-application';
import { JobApplicationComment } from 'src/app/models/job-application-comment';
import { User } from 'src/app/models/user';
import { JobApplicationCommentService } from 'src/app/services/job-application-comment.service';
import { JobApplicationCommentComponent } from '../job-application-comment/job-application-comment.component';

@Component({
  selector: 'app-job-application',
  templateUrl: './job-application.component.html',
  styleUrls: ['./job-application.component.css'],
})
export class JobApplicationComponent implements OnInit {
  constructor(
    private router: Router,
    private commentService: JobApplicationCommentService
  ) {}

  @Input() applications: JobApplication[] = [];
  @Input() activeUser: User | null = null;
  replyForm: boolean = false;
  newComment: JobApplicationComment = new JobApplicationComment();
  editComment: JobApplicationComment | null = null;
  selected: JobApplicationComment | null = null;
  comment: JobApplicationComment | null = null;
  comments: JobApplicationComment[] = [];
  replyComment: JobApplicationComment = new JobApplicationComment();
  replyApp: JobApplication | null = null;
  addButton: boolean = false;

  ngOnInit(): void {}

  goToJobs() {
    this.router.navigateByUrl('jobs');
  }

  create(newComment: JobApplicationComment, app: JobApplication) {
    if (this.activeUser)
      this.commentService
        .create(newComment, this.activeUser.id, app.id)
        .subscribe(
          (created) => {
            console.log('JobApplicationComment created');
            console.log(created);
            this.replyForm = false;
            this.newComment = new JobApplicationComment();
            this.addButton = false;
          },
          (fail) => {
            console.error(
              'Something went wrong during jobDetail creation',
              fail
            );
          }
        );
  }
  createReply(replyComment: JobApplicationComment, app: JobApplication) {
    if (this.activeUser)
      this.commentService
        .createReply(replyComment, this.activeUser.id, app.id)
        .subscribe(
          (created) => {
            console.log('JobApplicationComment created');
            console.log(created);
            this.replyComment = new JobApplicationComment();
            this.addButton = false;
          },
          (fail) => {
            console.error(
              'Something went wrong during jobDetail creation',
              fail
            );
          }
        );
  }

  // updateComment(editComment: JobApplicationComment, app: JobApplication) {
  //   if (this.activeUser)
  //     this.commentService.update(editComment, app.id, editComment.id).subscribe(
  //       (updated) => {
  //         console.log(
  //           'User JobApplicationComment has been updated successfully'
  //         );
  //         this.editComment = null;
  //         if (this.activeUser)
  //           this.loadJobApplicationComment(this.activeUser.id);
  //       },
  //       (fail) => {
  //         console.error('Something went wrong with updating jobDetail', fail);
  //       }
  //     );
  // }

  // loadJobApplicationComment(appId: number) {
  //   this.addButton = false;
  //   if (this.activeUser)
  //     this.commentService.userAppComments(this.activeUser.id, appId).subscribe(
  //       (data) => {
  //         this.comments = data;
  //         console.log('JobApplicationComments have been loaded');
  //       },
  //       (fail) => {
  //         console.error('Something went wrong loading user jobDetails', fail);
  //       }
  //     );
  // }

  // delete(app: JobApplication, comment: JobApplicationComment) {
  //   if (this.activeUser)
  //     this.commentService.destroy(app.id, comment.id).subscribe(
  //       (success) => {
  //         this.comment = null;
  //         if (this.activeUser)
  //           this.loadJobApplicationComment(this.activeUser.id);
  //         console.log('Successfully removed jobDetail', success);
  //       },
  //       (fail) => {
  //         console.error('Failed to remove user', fail);
  //       }
  //     );
  // }

  selectJobApplicationComment(comment: JobApplicationComment) {
    this.selected = comment;
    this.editComment = null;
  }

  setEditJobApplicationComment(ac: JobApplicationComment) {
    this.editComment = ac;
  }

  setAddButton() {
    this.addButton = true;
  }
}
