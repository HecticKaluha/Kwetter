import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProfileService} from "../profile.service";

@Component({
  selector: 'app-profiledata',
  templateUrl: './profiledata.component.html',
  styleUrls: ['./profiledata.component.css']
})
export class ProfiledataComponent implements OnInit {

  ammountOfFollowers: any;
  ammountOfFollowersLength: any;
  ammountOfFollowing: any;
  ammountOfFollowingLength: any;
  ammountOfkweets: any;
  ammountOfkweetsLength: any;
  @Input() username;

  constructor(protected httpClient: HttpClient, protected profileservice: ProfileService) {

  }

  public getOwnProfileDataAmmounts(username: String){
    //get ammount of followers
      this.profileservice.getFollowers(username).subscribe(res=> {
        this.ammountOfFollowers = res;
          if(this.ammountOfFollowers == null)
          {
            this.ammountOfFollowersLength = 0;
          }
          else {
            this.ammountOfFollowersLength = this.ammountOfFollowers.length;
          }
        this.ammountOfFollowersLength = this.ammountOfFollowers.length;
        console.log(this.ammountOfFollowersLength);
      },
      err => console.log(err),
      ()=> console.log("Done loading all the followers of " + this.username)
    );
      //get ammount of followings
    this.profileservice.getFollowing(username).subscribe(res=> {
        this.ammountOfFollowing = res;
        if(this.ammountOfFollowing == null)
        {
          this.ammountOfFollowingLength = 0;
        }
        else {
          this.ammountOfFollowingLength = this.ammountOfFollowing.length;
        }
      },
      err => console.log(err),
      ()=> console.log("Done loading all the followings of " + this.username)
    );
    //get profilekweets (ammount)
    this.profileservice.getOwnKweets(username).subscribe(res=> {
        this.ammountOfkweets = res;
        console.log(res);
        if(this.ammountOfkweets == null)
        {
          this.ammountOfkweetsLength = 0;
        }
        else {
          this.ammountOfkweetsLength = this.ammountOfkweets.length;
        }
      },
      err => console.log(err),
      ()=> console.log("Done loading all the kweets for data of " + this.username)
    );
  }

  ngOnInit() {
    this.getOwnProfileDataAmmounts(this.username);
  }

}
