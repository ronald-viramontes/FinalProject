import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Skill } from 'src/app/models/skill';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { SkillService } from 'src/app/services/skill.service';

@Component({
  selector: 'app-skill',
  templateUrl: './skill.component.html',
  styleUrls: ['./skill.component.css'],
})
export class SkillComponent implements OnInit {
  constructor(
    private skillService: SkillService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private auth: AuthService
  ) {}

  @Input() skills: Skill[] = [];
  @Input() activeUser: User | null = null;

  selected: Skill | null = null;
  newSkill: Skill = new Skill();
  editSkill: Skill | null = null;
  skillDetail: Skill | null = null;
  skill: Skill | null = null;
  addButton: boolean = false;
  ngOnInit(): void {}

  create(newSkill: Skill) {
    this.skillService.create(newSkill).subscribe(
      (created) => {
        console.log('Skill created');
        console.log(created);
        this.newSkill = new Skill();
        this.addButton = false;
        if (this.activeUser != null) this.loadSkills(this.activeUser.id);
      },
      (fail) => {
        console.error('Something went wrong during skill creation', fail);
      }
    );
  }

  updateSkill(editSkill: Skill) {
    if (this.activeUser)
      this.skillService
        .update(editSkill.id, this.activeUser.id, editSkill)
        .subscribe(
          (updated) => {
            console.log('User Skill has been updated successfully');
            this.editSkill = null;
            if (this.activeUser) this.loadSkills(this.activeUser.id);
          },
          (fail) => {
            console.error('Something went wrong with updating skill', fail);
          }
        );
  }

  loadSkills(userId: number) {
    this.addButton = false;
    this.skillService.userSkills(userId).subscribe(
      (data) => {
        this.skills = data;
        console.log('Skills have been loaded');
      },
      (fail) => {
        console.error('Something went wrong loading user skills', fail);
      }
    );
  }

  delete(skillId: number) {
    if (this.activeUser)
      this.skillService.destroy(skillId, this.activeUser.id).subscribe(
        (success) => {
          this.skillDetail = null;
          if (this.activeUser) this.loadSkills(this.activeUser.id);
          console.log('Successfully removed skill', success);
        },
        (fail) => {
          console.error('Failed to remove user', fail);
        }
      );
  }

  selectSkill(skill: Skill) {
    this.selected = skill;
    this.editSkill = null;
  }

  setEditSkill(sk: Skill) {
    this.editSkill = sk;
  }

  setAddButton() {
    this.addButton = true;
  }
}
