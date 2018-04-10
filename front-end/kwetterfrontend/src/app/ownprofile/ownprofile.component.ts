import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-ownprofile',
  templateUrl: './ownprofile.component.html',
  styleUrls: ['./ownprofile.component.css']
})
export class OwnprofileComponent implements OnInit {

  private apiUrl = "http://localhost:8080/kwetter/api/profile";
  details: any = {};
  username: any = "";
  bio: any = "";
  web: any = "";
  location: any = "";

  constructor(protected httpClient: HttpClient) {

  }

  public getProfile(profilename: string){

    return this.httpClient.get(`${this.apiUrl}/${profilename}`).subscribe(res=> {
        console.log(res);
        this.details = res;
        this.username = this.details.username;
        this.bio = this.details.bio;
        this.web = this.details.web;
        this.location = this.details.location;
      },
      err => console.log(err),
      ()=> console.log("Done loading all the kweets of " + profilename)
    );
  }

  ngOnInit() {
    //console.log("Fetching profile");
    this.getProfile("Hans");
  }

}
