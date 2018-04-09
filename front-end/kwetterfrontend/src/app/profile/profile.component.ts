import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  title = "Profilepage";
  //get profile
  private apiUrl = "http://localhost:8080/kwetter/api/profile/Klaartje";


  constructor(protected httpClient: HttpClient) {

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
  }
}


