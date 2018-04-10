import { Component, OnInit } from '@angular/core';
import {ProfileService} from "../profile.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Subscription} from "rxjs/Subscription";


@Component({
  selector: 'app-followers',
  templateUrl: './followers.component.html',
  styleUrls: ['./followers.component.css']
})
export class FollowersComponent implements OnInit {
  private username:string;
  private route$ : Subscription;
  private hasFollowers = false;
  followers: any = {};

  constructor(protected profileservice: ProfileService, private route : ActivatedRoute) { }

  public getFollowers(username: string){
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
      ()=> console.log("Done loading all the followers of " + username)
    );;
  }

  ngOnInit() {
    this.route$ = this.route.params.subscribe(
      (params : Params) => {
        this.username = params["username"];
      }
    );
    this.getFollowers(this.username);
  }

}
