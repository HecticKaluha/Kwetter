import {Component, Input, OnInit} from '@angular/core';
import {KweetService} from "../kweet.service";
import {ProfileService} from "../profile.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {WebsocketService} from "../websocket.service";
import {KweetwsService} from "../kweetws.service";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [ WebsocketService, KweetwsService ]
})
export class HomeComponent implements OnInit {

  private value;
  private result;
  private statusmessage: string;
  private loggedInUser;

  private route$: Subscription;

  private message:any;
  private receivedmessage:any;

  private allkweets;

  @Input() username;

  mostRecentKweet: any = {};
  private status: boolean;

  constructor(protected kweetService: KweetService, protected profileService: ProfileService, /*private websocketservice: WebsocketService, */ private kweetwsService: KweetwsService , private route: ActivatedRoute, private router: Router) {
    this.allkweets = [];
    kweetwsService.messages.subscribe(msg =>{
      //console.log("response from websocket: " + msg);
      //this.allkweets.push(msg);
      this.allkweets.push(
        {
          profilename: msg.profilename,
          message: msg.message,
          postdate: msg.postdate
        }
      );
    });
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
    this.message = {
      profilename: this.profileService.getLoggedInUser(),
      message: this.value
    };
    //console.log("username from message = " + this.message.profilename);
    this.kweetwsService.messages.next(this.message);
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
