import { Component, OnInit, Input } from '@angular/core';
import { Education } from 'src/app/models/education';
import { DeveloperService } from 'src/app/services/developer.service';

@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['./education.component.css']
})
export class EducationComponent implements OnInit {

  constructor(private developerService: DeveloperService) { }

  @Input() educations: Education[] = [];
  ngOnInit(): void {
  }

}
