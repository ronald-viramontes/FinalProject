import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JobPostComponent } from './components/job-post/job-post.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { UserHomeComponent } from './components/user-home/user-home.component';
import { AuthService } from './services/auth.service';

const routes: Routes = [
  { path: 'register', component: RegistrationComponent},
  { path: 'login', component: LoginComponent},
  { path: 'home', component: UserHomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {

  constructor(private authService: AuthService){}

  loggedIn(){
    return this.authService.checkLogin();
  }
}
