import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JobApplication } from 'src/app/models/job-application';
import { JobDetail } from 'src/app/models/job-detail';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { JobDetailService } from 'src/app/services/job-detail.service';

@Component({
  selector: 'app-job-detail',
  templateUrl: './job-detail.component.html',
  styleUrls: ['./job-detail.component.css'],
})
export class JobDetailComponent implements OnInit {
  constructor(
    private currentRoute: ActivatedRoute,
    private router: Router,
    private auth: AuthService,
    private jobDetailService: JobDetailService
  ) {}

  @Input() activeUser: User | null = null;
  @Input() jobApplication: JobApplication | null = null;
  jobDetails: JobDetail[] = [];
  selected: JobDetail | null = null;
  jobDetail: JobDetail | null = null;
  newJobDetail: JobDetail = new JobDetail();
  editJobDetail: JobDetail | null = null;
  addButton: boolean = false;


  ngOnInit(): void {}

  create(newJobDetail: JobDetail) {
    this.jobDetailService.create(newJobDetail).subscribe(
      (created) => {
        console.log('JobDetail created');
        console.log(created);
        this.newJobDetail = new JobDetail();
        this.addButton = false;
        if (this.activeUser != null) this.loadJobDetails(this.activeUser.id);
      },
      (fail) => {
        console.error('Something went wrong during jobDetail creation', fail);
      }
    );
  }

  updateJobDetail(editJobDetail: JobDetail) {
    if (this.activeUser)
      this.jobDetailService
        .update(editJobDetail.id, this.activeUser.id, editJobDetail)
        .subscribe(
          (updated) => {
            console.log('User JobDetail has been updated successfully');
            this.editJobDetail = null;
            if (this.activeUser) this.loadJobDetails(this.activeUser.id);
          },
          (fail) => {
            console.error('Something went wrong with updating jobDetail', fail);
          }
        );
  }

  loadJobDetails(userId: number) {
    this.addButton = false;
    this.jobDetailService.userJobDetails(userId).subscribe(
      (data) => {
        this.jobDetails = data;
        console.log('JobDetails have been loaded');
      },
      (fail) => {
        console.error('Something went wrong loading user jobDetails', fail);
      }
    );
  }

  delete(jobDetailId: number) {
    if (this.activeUser)
      this.jobDetailService.destroy(jobDetailId, this.activeUser.id).subscribe(
        (success) => {
          this.jobDetail = null;
          if (this.activeUser) this.loadJobDetails(this.activeUser.id);
          console.log('Successfully removed jobDetail', success);
        },
        (fail) => {
          console.error('Failed to remove user', fail);
        }
      );
  }

  selectJobDetail(jobDetail: JobDetail) {
    this.selected = jobDetail;
    this.editJobDetail = null;
  }

  setEditJobDetail(sk: JobDetail) {
    this.editJobDetail = sk;
  }

  setAddButton() {
    this.addButton = true;
  }
}




}
