import { Component, OnInit, Input, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Experience } from 'src/app/models/experience';
import { User } from 'src/app/models/user';

import { AuthService } from 'src/app/services/auth.service';
import { ExperienceService } from 'src/app/services/experience.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css'],
})
export class ExperienceComponent implements OnInit {
  constructor(
    private expService: ExperienceService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private auth: AuthService,
    private userService: UserService
  ) {}

  @Input() experiences: Experience[] = [];
  @Input() activeUser: User | null = null;

  selected: Experience | null = null;
  newExp: Experience = new Experience();
  editExp: Experience | null = null;
  expDetail: Experience | null = null;
  exp: Experience | null = null;
  addButton: boolean = false;
  ngOnInit(): void {}

  create(newExp: Experience) {
    this.expService.create(newExp).subscribe(
      (created) => {
        console.log('Experience created');
        console.log(created);
        this.newExp = new Experience();
        this.addButton = false;
        if (this.activeUser != null) this.loadExps(this.activeUser.id);
      },
      (fail) => {
        console.error('Something went wrong during exp creation', fail);
      }
    );
  }

  updateExp(editExp: Experience) {
    if (this.activeUser)
      this.expService.update(editExp.id, this.activeUser.id, editExp).subscribe(
        (updated) => {
          console.log('User Experience has updated successfully');
          this.editExp = null;
          if (this.activeUser) this.loadExps(this.activeUser.id);
        },
        (fail) => {
          console.error('Something went wrong with updating exp', fail);
        }
      );
  }

  loadExps(userId: number) {
    this.addButton = false;
    this.expService.userExperiences(userId).subscribe(
      (data) => {
        this.experiences = data;
        // this.editExp = null;
        // this.expDetail = null;
      },
      (err) => {
        console.error('Error retrieving skill list', err);
        console.error(err);
      }
    );
  }

  deleteExp(expId: number) {
    if (this.activeUser)
      this.expService.destroy(expId, this.activeUser.id).subscribe(
        (success) => {
          this.expDetail = null;
          if (this.activeUser) this.loadExps(this.activeUser.id);
          console.log('Successfully removed exp', success);
        },
        (fail) => {
          console.error('Failed to remove user', fail);
        }
      );
  }

  selectExp(exp: Experience) {
    this.selected = exp;
    this.editExp = null;
  }

  setEditExp(ex: Experience) {
    this.editExp = ex;
  }
  setAddButton() {
    this.addButton = true;
  }
}
