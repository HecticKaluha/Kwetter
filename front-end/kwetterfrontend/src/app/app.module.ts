import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from "@angular/forms";

import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { AppRoutingModule } from './app-routing.module';
import { OwnkweetsComponent } from './ownkweets/ownkweets.component';
import { FollowersComponent } from './followers/followers.component';
import { OwnprofileComponent } from './ownprofile/ownprofile.component';
import { ProfiledataComponent } from './profiledata/profiledata.component';
import {HttpClientModule} from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    HomeComponent,
    ProfileComponent,
    OwnkweetsComponent,
    FollowersComponent,
    OwnprofileComponent,
    ProfiledataComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
