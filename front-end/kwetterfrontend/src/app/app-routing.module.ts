import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import {LoginComponent} from "./login/login.component";
import {ProfilesComponent} from "./profiles/profiles.component";

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'home/:username', component: HomeComponent },
  { path: '', component: HomeComponent },
  { path : 'profile/:username', component : ProfileComponent},
  {path: 'login', component : LoginComponent},
  {path: 'profiles', component : ProfilesComponent},
  { path: 'profile', component: ProfileComponent }/*,
  { path: '**', redirectTo: '/home' }*/
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
