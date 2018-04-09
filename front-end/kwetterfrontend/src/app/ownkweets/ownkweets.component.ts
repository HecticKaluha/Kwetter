import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-ownkweets',
  templateUrl: './ownkweets.component.html',
  styleUrls: ['./ownkweets.component.css']
})
export class OwnkweetsComponent implements OnInit {

  private apiUrl = "http://localhost:8080/kwetter/api/profile/ownkweets";
  kweets: any;

  constructor(protected httpClient: HttpClient) {

  }
  public getOwnKweets(profilename: string){
    return this.httpClient.get(`${this.apiUrl}/${profilename}`).subscribe(res=> {
      console.log(res);
      this.kweets = res;
    },
      err => console.log(err),
      ()=> console.log("Done loading all the kweets of " + profilename)
    );
  }

  ngOnInit() {
    this.getOwnKweets("Hans");
  }

}
