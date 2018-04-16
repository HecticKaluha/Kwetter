import { Component, OnInit } from '@angular/core';
import {KweetService} from "../kweet.service";


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
  constructor(protected kweetService: KweetService) { }

  ngOnInit() {

  }

  public opentab(tab: string){

  }

  postKweet(){
    this.kweetService.postKweet(this.value, this.loggedInUser).subscribe(res=> this.statusmessage = "Kweet succesfully posted",
      (error) => this.statusmessage = "Something went wrong when posting your Kweet... Try again later.");
  }

}
