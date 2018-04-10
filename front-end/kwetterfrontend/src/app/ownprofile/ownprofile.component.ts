import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ProfileService} from "../profile.service";

@Component({
  selector: 'app-ownprofile',
  templateUrl: './ownprofile.component.html',
  styleUrls: ['./ownprofile.component.css']
})
export class OwnprofileComponent implements OnInit {

  details: any = {};
  username: any = "";
  bio: any = "";
  web: any = "";
  location: any = "";
  profilename = "Hans";

  constructor(protected httpClient: HttpClient, protected profileservive: ProfileService) {

  }

  public getProfile(){
    return this.profileservive.getProfileData(this.profilename).subscribe(res=> {
        console.log(res);
        this.details = res;
        this.username = this.details.username;
        this.bio = this.details.bio;
        this.web = this.details.web;
        this.location = this.details.location;
      },
      err => console.log(err),
      ()=> console.log("Done loading all the kweets of " + this.profilename)
    );
  }

  ngOnInit() {
    this.getProfile();
  }

}
