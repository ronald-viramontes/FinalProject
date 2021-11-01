import { Component, OnInit, Input } from '@angular/core';
import { Education } from 'src/app/models/education';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { EducationService } from 'src/app/services/education.service';

@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['./education.component.css']
})
export class EducationComponent implements OnInit {

  constructor(private educationService: EducationService, private authService: AuthService) { }

  @Input() educations: Education[] = [];
  @Input() activeUser: User | null = null;
  selected: Education | null = null;
  newEducation: Education = new Education();
  createForm: boolean = false;
  editEducation: Education | null = null;
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

  loadEducationsByDevId(userId: number) {
    this.educationService.show(userId).subscribe(
      data => {
        this.educations = data;
      },
      err => {
        console.error(err);
      }
    );
  }

  edit(education: Education, edId: number) {
    if(this.activeUser)
    this.educationService.edit(education, edId, this.activeUser.id).subscribe(
      data => {
        console.log('Update Successful');
        if(this.activeUser)
        this.loadEducationsByDevId(this.activeUser.id);
      },
      err => {
        console.error(err);
        console.error('Error updating education');
      }
    );
  }

  delete(edId: number) {
    if(this.activeUser)
    this.educationService.delete(edId, this.activeUser.id).subscribe(
      data => {
        if(this.activeUser)
          this.loadEducationsByDevId(this.activeUser.id);
        console.log('education deleted successfully');
      },
      err => {
        console.error(err);
      }
    );
  }

  selectEducation(edu: Education) {
    this.selected = new Education();
    this.selected.id = edu.id;
    this.selected.completeDate = edu.completeDate;
    this.selected.degreeCertificateName = edu.degreeCertificateName;
    this.selected.educationType = edu.educationType;
    this.selected.institutionName = edu.institutionName;
  }

  create(newEd: Education) {
    if (this.activeUser) {
      newEd.user = null;
      this.educationService.create(newEd, this.activeUser?.id).subscribe(
        data => {
          if(this.activeUser)
          this.loadEducationsByDevId(this.activeUser.id);
          return data;
        },
        err => {
          console.error(err);
        }
      );
    }
  }

  setEdit(edu: Education){
    this.editEducation = new Education();
    this.editEducation.institutionName =  edu.institutionName;
    this.editEducation.educationType = edu.educationType;
    this.editEducation.degreeCertificateName = edu.degreeCertificateName;
    this.editEducation.completeDate = edu.completeDate;
  }
}
