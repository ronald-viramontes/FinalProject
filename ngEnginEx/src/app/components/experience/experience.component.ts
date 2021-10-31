import { Component, OnInit, Input, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Developer } from 'src/app/models/developer';
import { Experience } from 'src/app/models/experience';

import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { DeveloperService } from 'src/app/services/developer.service';
import { ExperienceService } from 'src/app/services/experience.service';
import { UserService } from 'src/app/services/user.service';
import { DeveloperComponent } from '../developer/developer.component';
import { DisplayComponent } from '../display/display.component';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css'],
})
export class ExperienceComponent implements OnInit {
  constructor(
    private expService: ExperienceService,
    private devSvc: DeveloperService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private auth: AuthService,
    private dispComp: DisplayComponent,

    private userService: UserService
  ) {}

  @Input() experiences: Experience[] = [];
  // @Input() activeUser: User | null = null;
  @Input() activeDev: User | null = null;
  @Input() experience: Experience | null = null;
  @Output() editDev: Developer | null = null;
  devs: Developer[] = [];
  @Input() activeUser: User | null = null;
  dev: Developer | null = null;
  devExperiences: Experience[] = [];
  exps: Experience[] = [];
  developer: Developer | null = null;
  selected: Experience | null = null;
  exp: Experience | null = null;
  expnce: Experience | null = null;
  editExperience: Experience | null = null;
  newExperience: Experience = new Experience();
  tableExperience: Experience = new Experience();
  id: number = 0;
  jobTypeTab: number = 1;

  ngOnInit(): void {
    console.log(this.activeUser);
    let id = this.currentRoute.snapshot.params['id'];
    if (this.currentRoute.snapshot.paramMap.get('id')) {
      this.expService.show(id).subscribe(
        (found) => {
          this.selected = found;
        },
        (notFound) => {
          console.error('Experience not found');
          console.error(notFound);
          this.router.navigateByUrl('**');
        }
      );
    } else {
      this.loadDevelopers();
      // this.reloadExperiences();
    }
  }

  addExperience(newExperience: Experience) {
    this.expService.create(newExperience).subscribe(
      (created) => {
        console.log('Experience created');
        console.log(created);
        this.loadDevelopers();
        this.newExperience = new Experience();
      },
      (fail) => {
        console.error('Something went wrong during exp creation', fail);
      }
    );
  }

  // getActiveUserAndDev() {
  //   let creds = this.auth.getCredentials();
  //   if (creds != null) {
  //     creds = atob(creds);
  //     let unArr = creds.split(':');
  //     let username = unArr[0];
  //     this.userService.show(username).subscribe(
  //       (data) => {
  //         this.activeUser = data;
  //         this.activeDev = data.developer;
  //       },
  //       (err) => {
  //         console.error(err);
  //       }
  //     );
  //   }
  // }

  updateExperience(exp: Experience, editDev: Developer) {
    this.expService.update(exp.id, editDev.id, exp).subscribe(
      (updated) => {
        this.exp = updated;
        this.displayTable();
      },
      (fail) => {
        console.error('Something went wrong with updating exp', fail);
      }
    );
  }
  // updateDeveloper(editDev: Developer, exp: Experience) {
  //   this.updateExperience(exp, editDev);
  //   this.devSvc.update(editDev.id, editDev).subscribe(
  //     (updated) => {
  //       this.editDev = updated;
  //       this.editDev = null;
  //       this.displayTable();
  //     },
  //     (fail) => {
  //       console.error('Something went wrong with updating client', fail);
  //     }
  //   );
  // }

  showDevExperiences(id: number) {
    this.id = id;
    this.expService.devExperienceIndex(id).subscribe(
      (data) => {
        this.devExperiences = data;
        console.log('devExperiences: ' + this.devExperiences);
      },
      (fail) => {
        console.error(
          'Something went wrong with the developer exps list',
          fail
        );
      }
    );
  }

  reloadExperiences(): void {
    this.expService.index().subscribe(
      (data) => {
        this.exps = data;
      },
      (err) => {
        console.error('Error retrieving skill list', err);
        console.error(err);
      }
    );
  }
  loadDevelopers(): void {
    this.devSvc.index().subscribe(
      (data) => {
        this.devs = data;
      },
      (err) => {
        console.error('Error retrieving developers');
        console.error(err);
      }
    );
  }

  deleteExperience(expId: number, devId: number) {
    this.expService.destroy(expId, devId).subscribe(
      (success) => {
        this.editExperience = null;
        this.reloadExperiences();
        console.log('Successfully removed exp', success);
      },
      (fail) => {
        console.error('Failed to remove user', fail);
      }
    );
  }

  displayExperience(expnce: Experience, dev: Developer) {
    this.editDev = dev;
    this.editExperience = expnce;
  }

  displayTable() {
    this.loadDevelopers();
    return (this.editExperience = null), (this.editDev = null);
  }

  setEditExperience() {
    this.editExperience = Object.assign({}, this.selected);
  }

  displayJobPostTiles() {
    this.jobTypeTab++;
  }
}
