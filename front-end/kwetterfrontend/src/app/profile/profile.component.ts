import {Component, Input, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ActivatedRoute, Params} from "@angular/router";
import {Subscription} from "rxjs/Subscription";


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  title = "Profilepage";
  private route$ : Subscription;
  @Input() username;


  constructor(private route : ActivatedRoute) {

  }

  ngOnInit() {
    this.route$ = this.route.params.subscribe(
      (params : Params) => {
        this.username = params["username"];

      }
    );
  }
}


