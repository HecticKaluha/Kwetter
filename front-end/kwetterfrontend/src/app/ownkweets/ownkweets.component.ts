import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {ProfileService} from '../profile.service';

@Component({
  selector: 'app-ownkweets',
  templateUrl: './ownkweets.component.html',
  styleUrls: ['./ownkweets.component.css']
})
export class OwnkweetsComponent implements OnInit {

  kweets: any;
  @Input() username;
  private hasKweets = false;

  constructor(protected profileservice: ProfileService) {

  }

  public getOwnKweets(profilename: string) {
    this.profileservice.getOwnKweets(profilename).subscribe((res: any) => {
        console.log("getownkweets", res);
        if (res[0] != null) {
          this.kweets = res.map(kweet => {
            return {...kweet, postDate: new Date(kweet.postDate).toLocaleDateString()};
          });
          this.hasKweets = true;
        }
        else {
          this.kweets = null;
          this.hasKweets = false;
        }
      },
      err => console.log(err),
      () => console.log("Done loading all the kweets of " + profilename)
    );
  }

  ngOnInit() {

  }

  ngOnChanges() {
    this.getOwnKweets(this.username);
  }

}
