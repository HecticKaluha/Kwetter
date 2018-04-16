import { Component, OnInit } from '@angular/core';
import {KweetService} from "../kweet.service";
import {ProfileService} from "../profile.service";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private value;
  private result;
  private statusmessage: string;
  private loggedInUser = "Hans";
  mostRecentKweet:any = {};
  private status: boolean;
  constructor(protected kweetService: KweetService,protected profileService: ProfileService) { }

  ngOnInit() {
    this.getMostRecentKweet(this.loggedInUser);
  }
  ngOnChanges() {
    this.getMostRecentKweet(this.loggedInUser);
  }

  public opentab(tab: string){

  }

  postKweet(){
    this.kweetService.postKweet(this.value, this.loggedInUser).subscribe(res=> this.statusmessage = "Kweet succesfully posted",
      (error) => this.statusmessage = "Something went wrong when posting your Kweet... Try again later.");
  }

  getMostRecentKweet(loggedInUser:string){
    this.profileService.getMostRecentKweet(loggedInUser).subscribe(kweet => {
      this.mostRecentKweet = kweet;
      this.status = true;
      },
      (error) =>
      {
        this.statusmessage = "Something went wrong when retrieving the most recent kweet... Try again later.";
        this.status = false;
      });
  }

}
