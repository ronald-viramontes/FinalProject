import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DisplayComponent } from './components/display/display.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { JobPostComponent } from './components/job-post/job-post.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { SendChatComponent } from './components/send-chat/send-chat.component';
import { AuthService } from './services/auth.service';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'register', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: DisplayComponent },
  { path: 'jobs', component: JobPostComponent },
  { path: 'editprofile', component: EditProfileComponent },
  { path: 'sendchat', component: SendChatComponent },
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
