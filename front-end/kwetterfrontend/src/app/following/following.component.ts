import {Component, Input, OnInit} from '@angular/core';
import {ProfileService} from "../profile.service";

@Component({
  selector: 'app-following',
  templateUrl: './following.component.html',
  styleUrls: ['./following.component.css']
})
export class FollowingComponent implements OnInit {

  @Input() username;
  private following: any = {};
  private hasFollowing = false;


  constructor(protected profileservice: ProfileService) { }

  ngOnInit() {
  }

  ngOnChanges() {
    this.getFollowing(this.username);
  }

  private getFollowing(username: string) {
    this.profileservice.getFollowing(username).subscribe(res=> {
        if(res[0] != null)
        {
          this.following = res;
          console.log(this.following);
          this.hasFollowing = true;
        }
        else{
          this.following = null;
          this.hasFollowing = false;
        }
      },
      err => console.log(err),
      ()=> console.log("Done loading all the followings of " + username)
    );
  }
}
