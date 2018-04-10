import { Component, OnInit } from '@angular/core';
import {ProfileService} from "../profile.service";


@Component({
  selector: 'app-followers',
  templateUrl: './followers.component.html',
  styleUrls: ['./followers.component.css']
})
export class FollowersComponent implements OnInit {
  private username = "Hans";
  private hasFollowers = false;
  followers: any = {};

  constructor(protected profileservice: ProfileService) { }

  public getFollowers(){
    this.profileservice.getFollowers(this.username).subscribe(res=> {
      console.log("The returned data is: ");
      console.log(res);
      if(res[0] != null)
      {
        this.followers = res;
        console.log(this.followers);
        this.hasFollowers = true;
      }
    },
      err => console.log(err),
      ()=> console.log("Done loading all the followers of " + this.username)
    );;
  }

  ngOnInit() {
    this.getFollowers();
  }

}
