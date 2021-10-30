import { Component, OnInit, Input } from '@angular/core';
import { Developer } from 'src/app/models/developer';
import { Experience } from 'src/app/models/experience';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  constructor(private authService: AuthService, private userService: UserService) { }

  @Input() experiences: Experience[] = [];
  @Input() activeUser: User | null = null;
  developer: Developer | null = null;

  ngOnInit(): void {
  }
}
