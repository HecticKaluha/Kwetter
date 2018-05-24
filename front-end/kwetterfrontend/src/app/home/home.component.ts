import {Component, Input, OnInit} from '@angular/core';
import {KweetService} from "../kweet.service";
import {ProfileService} from "../profile.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {WebsocketService} from "../websocket.service";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private value;
  private result;
  private statusmessage: string;
  private loggedInUser;

  private route$: Subscription;

  @Input() username;

  mostRecentKweet: any = {};
  private status: boolean;

  constructor(protected kweetService: KweetService, protected profileService: ProfileService, private websocketservice: WebsocketService, private route: ActivatedRoute, private router: Router) {

  }

  ngOnInit() {
    this.route$ = this.route.params.subscribe(
      (params: Params) => {
        this.loggedInUser = this.profileService.getLoggedInUser();
      }
    );


    if (this.loggedInUser != null)
      this.getMostRecentKweet(this.loggedInUser);
  }

  ngOnChanges() {
    if (this.loggedInUser != null)
      this.getMostRecentKweet(this.loggedInUser);
  }

  public opentab(tab: string) {

  }

  postKweet() {
    this.websocketservice.sendMessage(this.value, this.profileService.getLoggedInUser());
    this.kweetService.postKweet(this.value, this.loggedInUser).subscribe(res => {
        this.statusmessage = "Kweet succesfully posted";
        this.getMostRecentKweet(this.loggedInUser);
      },
      (error) => this.statusmessage = "Something went wrong when posting your Kweet... Try again later."
    );
  }

  getMostRecentKweet(loggedInUser: string) {
    this.profileService.getMostRecentKweet(loggedInUser).subscribe(kweet => {
        this.mostRecentKweet = kweet;
        this.status = true;
      },
      (error) => {
        this.statusmessage = "Something went wrong when retrieving the most recent kweet... Try again later.";
        this.status = false;
      });
  }

  public onChange(value) {
    if (value == "Logout") {
      console.log("uitgelogd");
    }
    else {

      this.router.navigateByUrl('/profile/' + value);
      console.log("route naar profile");
    }
  }

}
