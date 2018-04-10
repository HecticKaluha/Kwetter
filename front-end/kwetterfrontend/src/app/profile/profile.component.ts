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
  //get profile
  private apiUrl = "http://localhost:8080/kwetter/api/profile/Klaartje";
  @Input() username;


  constructor(protected httpClient: HttpClient, private route : ActivatedRoute) {

  }

  public createCORSRequest(method, url) {
    var xhr = new XMLHttpRequest();
    if ("withCredentials" in xhr) {
      // XHR for Chrome/Firefox/Opera/Safari.
      xhr.open(method, url, true);
    }
    else {
      // CORS not supported.
      xhr = null;
    }
    return xhr;
  }

  ngOnInit() {
    this.route$ = this.route.params.subscribe(
      (params : Params) => {
        this.username = params["username"];

      }
    );
  }
}


