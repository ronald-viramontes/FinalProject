import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Developer } from 'src/app/models/developer';
import { Skill } from 'src/app/models/skill';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { DeveloperService } from 'src/app/services/developer.service';
import { SkillService } from 'src/app/services/skill.service';

@Component({
  selector: 'app-skill',
  templateUrl: './skill.component.html',
  styleUrls: ['./skill.component.css'],
})
export class SkillComponent implements OnInit {
  skills: Skill[] = [];
  skill: Skill = new Skill();
  newSkill: Skill = new Skill();
  editSkill: Skill | null = null;
  selected: Skill | null = null;
  devSkills: Skill[] = [];
  developer: Developer = new Developer();

  id: number = 0;
  constructor(
    private skillService: SkillService,
    private devService: DeveloperService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private auth: AuthService
  ) {}

  ngOnInit(): void {
    let id = this.currentRoute.snapshot.params['id'];
    if (this.currentRoute.snapshot.paramMap.get('id')) {
      this.skillService.show(id).subscribe(
        (found) => {
          this.selected = found;
        },
        (notFound) => {
          console.error('Skill not found');
          console.error(notFound);
          this.router.navigateByUrl('**');
        }
      );
    } else {
      this.reloadSkills();
    }
  }

  addSkill(newSkill: Skill) {
    this.skillService.create(newSkill).subscribe(
      (created) => {
        console.log('Skill created');
        console.log(created);
        this.ngOnInit();
        this.reloadSkills;
        this.newSkill = new Skill();
      },
      (fail) => {
        console.error('Something went wrong during skill creation', fail);
      }
    );
  }

  updateSkill(skill: Skill) {
    this.skillService.update(skill, skill.id).subscribe(
      (updated) => {
        this.skill = updated;
        this.editSkill = null;
        this.displayTable();
      },
      (fail) => {
        console.error('Something went wrong with updating skill', fail);
      }
    );
  }

  showDevSkills(id: number) {
    this.skillService.devSkillIndex(id).subscribe(
      (data) => {
        this.devSkills = data;
        this.reloadSkills();
      },
      (fail) => {
        console.error(
          'Something went wrong with the developer skills list',
          fail
        );
      }
    );
  }

  reloadSkills(): void {
    this.skillService.index().subscribe(
      (data) => {
        this.skills = data;
      },
      (err) => {
        console.error('Error retrieving skill list', err);
        console.error(err);
      }
    );
  }

  deleteSkill(skillId: number) {
    this.skillService.destroy(skillId).subscribe(
      (success) => {
        this.editSkill = null;
        this.reloadSkills();
        console.log('Successfully removed skill', success);
      },
      (fail) => {
        console.error('Failed to remove user', fail);
      }
    );
  }

  displaySkill(skill: Skill) {
    this.editSkill = skill;
  }

  displayTable() {
    this.reloadSkills();
    return (this.editSkill = null);
  }

  setEditSkill() {
    this.editSkill = Object.assign({}, this.selected);
  }
}
