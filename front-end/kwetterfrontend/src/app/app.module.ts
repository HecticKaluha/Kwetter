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
import { ProfileService } from './profile.service';
import { KweetService } from './kweet.service';
import { FollowingComponent } from './following/following.component';
import { TrendsComponent } from './trends/trends.component';
import { TimelineComponent } from './timeline/timeline.component';



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
    ProfiledataComponent,
    FollowingComponent,
    TrendsComponent,
    TimelineComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [
    ProfileService,
    KweetService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
