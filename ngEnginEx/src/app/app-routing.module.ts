import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DisplayComponent } from './components/display/display.component';
import { EditJobComponent } from './components/edit-job/edit-job.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { EducationComponent } from './components/education/education.component';
import { ExperienceComponent } from './components/experience/experience.component';
import { JobApplicationComponent } from './components/job-application/job-application.component';
import { JobListingComponent } from './components/job-listing/job-listing.component';
import { JobPostComponent } from './components/job-post/job-post.component';
import { LoginComponent } from './components/login/login.component';
import { MyJobPostsComponent } from './components/my-job-posts/my-job-posts.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { SendChatComponent } from './components/send-chat/send-chat.component';
import { SkillComponent } from './components/skill/skill.component';
import { VisitorHomeComponent } from './components/visitor-home/visitor-home.component';
import { AuthService } from './services/auth.service';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'register', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: DisplayComponent },
  { path: 'myjobs', component: MyJobPostsComponent },
  { path: 'applications', component: JobApplicationComponent },
  { path: 'educations', component: EducationComponent },
  { path: 'experiences', component: ExperienceComponent },
  { path: 'skills', component: SkillComponent },
  { path: 'jobs', component: JobPostComponent },
  { path: 'editprofile', component: EditProfileComponent },
  { path: 'sendchat', component: SendChatComponent },
  { path: 'listjob', component: JobListingComponent },
  { path: 'visitor-home', component: VisitorHomeComponent },
  { path: 'editjobform', component: EditJobComponent },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      useHash: true,
      scrollPositionRestoration: 'enabled',
    }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {
  constructor(private authService: AuthService) {}

  loggedIn() {
    return this.authService.checkLogin();
  }
}
