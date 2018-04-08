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
  data: any = {};

  constructor(protected httpClient: HttpClient) {

  }

  public getProfile(profilename: string){
    console.log(this.httpClient.get(`${this.apiUrl}/${profilename}`));
    return this.httpClient.get(`${this.apiUrl}/${profilename}`);

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
    console.log("Fetching profile");
    //var url = 'http://localhost:8080/kwetter/api/profile/Klaartje';
    //var xhr = this.createCORSRequest('GET', url);
    //console.log(xhr.send());
    this.httpClient.get('http://localhost:8080/kwetter/api/profile/Hans').subscribe(res=> { console.log(res);});
    //this.getProfile("Hans");
  }
}


