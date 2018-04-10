import { Component, OnInit } from '@angular/core';
import {ProfileService} from "../profile.service";

@Component({
  selector: 'app-followers',
  templateUrl: './followers.component.html',
  styleUrls: ['./followers.component.css']
})
export class FollowersComponent implements OnInit {
  private username = "Hans";
  followers: any;

  constructor(protected profileservice: ProfileService) { }

  public getFollowers(){
    this.profileservice.getFollowers(this.username).subscribe(res=> {
        this.followers = res;
      },
      err => console.log(err),
      ()=> console.log("Done loading all the followers of " + this.username)
    );;
  }

  ngOnInit() {
    this.getFollowers();
  }

}
