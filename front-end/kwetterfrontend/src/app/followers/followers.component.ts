import {Component, Input, OnInit} from '@angular/core';
import {ProfileService} from "../profile.service";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-followers',
  templateUrl: './followers.component.html',
  styleUrls: ['./followers.component.css']
})
export class FollowersComponent implements OnInit {
  private route$ : Subscription;
  private hasFollowers = false;
  followers: any = {};

  @Input() username;

  constructor(protected profileservice: ProfileService) { }

  public getFollowers(username: string){
    this.profileservice.getFollowers(this.username).subscribe(res=> {
      if(res[0] != null)
      {
        this.followers = res;
        console.log("followers",this.followers);
        this.hasFollowers = true;
      }
      else{
        this.followers = null;
        this.hasFollowers = false;
      }
    },
      err => console.log(err),
      ()=> console.log("Done loading all the followers of " + username)
    );
  }

  ngOnInit() {

  }
  ngOnChanges() {
    this.getFollowers(this.username);
  }

}
