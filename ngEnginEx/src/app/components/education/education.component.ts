import { Component, OnInit, Input } from '@angular/core';
import { Developer } from 'src/app/models/developer';
import { Education } from 'src/app/models/education';
import { DeveloperService } from 'src/app/services/developer.service';
import { EducationService } from 'src/app/services/education.service';

@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['./education.component.css']
})
export class EducationComponent implements OnInit {

  constructor(private developerService: DeveloperService, private educationService: EducationService) { }

  @Input() educations: Education[] = [];
  @Input() selectedDev: Developer | null = null;
  selected: Education | null = null;
  ngOnInit(): void {
    // this.loadEducations();
  }

  loadEducations() {
    this.educationService.index().subscribe(
      data => {
        this.educations = data;
      },
      err => {
        console.error(err);
      }
    );
  }

  loadEducationsByDevId(devId: number) {
    this.educationService.show(devId).subscribe(
      data => {
        this.educations = data;
      },
      err => {
        console.error(err);
      }
    );
  }

  edit(education: Education, edId: number, devId: number) {
    this.educationService.edit(education, edId, devId).subscribe(
      data => {
        console.log('Update Successful');

      },
      err => {
        console.error(err);
        console.error('Error updating education');
      }
    )
  }

  delete(edId: number) {
    this.educationService.delete(edId).subscribe(
      data => {
        console.log('education deleted successfully');
      },
      err => {
        console.error(err);
      }
    )
  }

}
