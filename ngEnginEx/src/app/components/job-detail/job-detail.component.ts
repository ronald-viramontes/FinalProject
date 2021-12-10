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
  @Input() applications: JobApplication[] = [];
  app: JobApplication | null = null;
  selected: JobDetail | null = null;
  detail: JobDetail | null = null;
  newJobDetail: JobDetail = new JobDetail();
  editJobDetail: JobDetail | null = null;
  addButton: boolean = false;

  ngOnInit(): void {}

  starRatingHallow: string = '⭒⭒⭒⭒⭒⭒⭒⭒⭒⭒';
  starRatingSolid: string = '⭑⭑⭑⭑⭑⭑⭑⭑⭑⭑';
  howManyStars(rating: number) {
    let total = 10 - rating;
    let hallow: string = '';
    let solid: string = '';
    hallow = this.starRatingHallow.slice(0, total);
    solid = this.starRatingSolid.slice(0, rating);
    let newStarRating = solid.concat(hallow);
    return newStarRating;
  }

  create(newJobDetail: JobDetail, app: JobApplication) {
    if (this.app)
      this.jobDetailService.create(newJobDetail, app.id).subscribe(
        (created) => {
          console.log('JobDetail created');
          console.log(created);
          this.newJobDetail = new JobDetail();
          this.addButton = false;
          if (this.activeUser != null) this.loadJobDetail(this.activeUser.id);
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
            if (this.activeUser) this.loadJobDetail(this.activeUser.id);
          },
          (fail) => {
            console.error('Something went wrong with updating jobDetail', fail);
          }
        );
  }

  loadJobDetail(appId: number) {
    this.addButton = false;
    this.jobDetailService.userAppJobDetail(appId).subscribe(
      (data) => {
        this.detail = data;
        console.log('JobDetails have been loaded');
      },
      (fail) => {
        console.error('Something went wrong loading user jobDetails', fail);
      }
    );
  }

  delete(jobDetailId: number, appId: number) {
    if (this.activeUser)
      this.jobDetailService.destroy(jobDetailId, this.activeUser.id).subscribe(
        (success) => {
          this.detail = null;
          if (this.activeUser) this.loadJobDetail(this.activeUser.id);
          console.log('Successfully removed jobDetail', success);
        },
        (fail) => {
          console.error('Failed to remove user', fail);
        }
      );
  }

  selectJobDetail(detail: JobDetail) {
    this.selected = detail;
    this.editJobDetail = null;
  }

  setEditJobDetail(jd: JobDetail) {
    this.editJobDetail = jd;
  }

  setAddButton() {
    this.addButton = true;
  }
}
