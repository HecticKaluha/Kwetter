import {Component, Input, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ProfileService} from "../profile.service";

@Component({
  selector: 'app-ownprofile',
  templateUrl: './ownprofile.component.html',
  styleUrls: ['./ownprofile.component.css']
})
export class OwnprofileComponent implements OnInit {

  details: any = {};
  profilename: any = "";
  bio: any = "";
  web: any = "";
  location: any = "";
  profilePicture: any = "";
  @Input() username;

  constructor(protected httpClient: HttpClient, protected profileservive: ProfileService) {

  }

  public getProfile(username: String){
    return this.profileservive.getProfileData(username).subscribe(res=> {
        console.log("ownerprofile",res);
        this.details = res;
        this.profilename = this.details.username;
        this.bio = this.details.bio;
        this.web = this.details.web;
        this.location = this.details.location;
        this.profilePicture = this.details.profilePictureUrl;
      },
      err => console.log(err),
      ()=> console.log("Done loading the profile of " + this.profilename)
    );
  }

  ngOnInit() {

  }
  ngOnChanges(){
    this.getProfile(this.username);
  }

}
