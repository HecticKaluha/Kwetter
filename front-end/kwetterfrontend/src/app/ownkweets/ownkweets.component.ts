import {Component, Input, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-ownkweets',
  templateUrl: './ownkweets.component.html',
  styleUrls: ['./ownkweets.component.css']
})
export class OwnkweetsComponent implements OnInit {

  kweets: any;
  @Input() username;

  constructor(protected profileservice: ProfileService) {

  }
  public getOwnKweets(profilename: string){
    this.profileservice.getOwnKweets(profilename).subscribe(res=> {
        console.log(res);
        this.kweets = res;
      },
      err => console.log(err),
      ()=> console.log("Done loading all the kweets of " + profilename)
    );
  }

  ngOnInit() {
    this.getOwnKweets(this.username);
  }

}
