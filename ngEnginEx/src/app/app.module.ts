import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DeveloperComponent } from './components/developer/developer.component';
import { SkillComponent } from './components/skill/skill.component';
import { ExperienceComponent } from './components/experience/experience.component';
import { EducationComponent } from './components/education/education.component';
import { ClientComponent } from './components/client/client.component';
import { CompanyComponent } from './components/company/company.component';
import { PostComponent } from './components/post/post.component';
import { JobPostComponent } from './components/job-post/job-post.component';
import { JobStatusComponent } from './components/job-status/job-status.component';
import { JobDetailComponent } from './components/job-detail/job-detail.component';
import { JobApplicationComponent } from './components/job-application/job-application.component';
import { JobApplicationCommentComponent } from './components/job-application-comment/job-application-comment.component';
import { UserComponent } from './components/user/user.component';
import { HttpClientModule } from '@angular/common/http';
import { NgbAccordionModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { ClientService } from './services/client.service';
import { DeveloperService } from './services/developer.service';
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

@NgModule({
  declarations: [
    AppComponent,
    ClientComponent,
    CompanyComponent,
    DeveloperComponent,
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
    PostComponent,
    RegistrationComponent,
    SkillComponent,
    UserComponent,
    UserHomeComponent,
    JobSearchComponent,
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
    ClientService,
    DeveloperService,
    EducationService,
    ExperienceService,
    JobApplicationService,
    JobPostService,
    SkillService,
    UserService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
