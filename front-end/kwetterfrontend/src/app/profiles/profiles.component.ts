import { Component, OnInit } from '@angular/core';
import {ProfileService} from "../profile.service";

@Component({
  selector: 'app-profiles',
  templateUrl: './profiles.component.html',
  styleUrls: ['./profiles.component.css']
})
export class ProfilesComponent implements OnInit {

  private profiles: any = {};
  private areprofiles: boolean;
  constructor(private profileservice:ProfileService) { }

  ngOnInit() {
    this.getAllProfiles();
  }

  public getAllProfiles(){
    this.profileservice.getAllProfiles().subscribe(res=> {
        if(res[0] != null)
        {
          this.profiles = res;
          this.areprofiles = true;
        }
        else{
          this.profiles = null;
          this.areprofiles = false;
        }
      },
      err => console.log(err),
      ()=> console.log("Done loading all the profiles")
    );
  }

  follow(profileToFollow:string){
    this.profileservice.follow(profileToFollow).subscribe(res=> {
        console.log("result is", res)
        ;
      },
      err => {
      console.log(err);
        alert("Je volgt nu " + profileToFollow);
      },
      ()=> console.log("You are now following " + profileToFollow)
    );
  }


}
