import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SkillComponent } from './components/skill/skill.component';
import { ExperienceComponent } from './components/experience/experience.component';
import { EducationComponent } from './components/education/education.component';
import { CompanyComponent } from './components/company/company.component';
import { JobPostComponent } from './components/job-post/job-post.component';
import { JobStatusComponent } from './components/job-status/job-status.component';
import { JobDetailComponent } from './components/job-detail/job-detail.component';
import { JobApplicationComponent } from './components/job-application/job-application.component';
import { JobApplicationCommentComponent } from './components/job-application-comment/job-application-comment.component';
import { UserComponent } from './components/user/user.component';
import { HttpClientModule } from '@angular/common/http';
import { NgbAccordionModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { JobApplicationService } from './services/job-application.service';
import { JobPostService } from './services/job-post.service';
import { AuthService } from './services/auth.service';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { UserHomeComponent } from './components/user-home/user-home.component';
import { SkillService } from './services/skill.service';
import { DisplayComponent } from './components/display/display.component';
import { ExperienceService } from './services/experience.service';
import { EducationService } from './services/education.service';
import { UserService } from './services/user.service';
import { JobSearchComponent } from './components/job-search/job-search.component';
import { DateSortPipe } from './pipes/date-sort.pipe';
import { UserJobPipe } from './pipes/user-job.pipe';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { JobApplicationCommentService } from './services/job-application-comment.service';
import { JobDetailService } from './services/job-detail.service';
import { OpenJobPipe } from './pipes/open-job.pipe';

@NgModule({
  declarations: [
    AppComponent,
    CompanyComponent,
    DisplayComponent,
    EducationComponent,
    ExperienceComponent,
    JobApplicationComponent,
    JobApplicationCommentComponent,
    JobDetailComponent,
    JobPostComponent,
    JobStatusComponent,
    LoginComponent,
    NavigationComponent,
    RegistrationComponent,
    SkillComponent,
    UserComponent,
    UserHomeComponent,
    JobSearchComponent,
    EditProfileComponent,
    DateSortPipe,
    UserJobPipe,
    OpenJobPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    NgbAccordionModule,
  ],
  providers: [
    AuthService,
    EducationService,
    ExperienceService,
    JobApplicationService,
    JobApplicationCommentService,
    JobDetailService,
    JobPostService,
    SkillService,
    UserService,
    DateSortPipe,
    UserJobPipe,
    OpenJobPipe
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
