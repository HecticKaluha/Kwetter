import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-ownprofile',
  templateUrl: './ownprofile.component.html',
  styleUrls: ['./ownprofile.component.css']
})
export class OwnprofileComponent implements OnInit {

  private apiUrl = "http://localhost:8080/kwetter/api/profile";
  data: any = {};

  constructor(protected httpClient: HttpClient) {

  }

  public getProfile(profilename: string){
    console.log(this.httpClient.get(`${this.apiUrl}/${profilename}`));
    return this.httpClient.get(`${this.apiUrl}/${profilename}`).subscribe(res=> {
      console.log(res);
      this.data = res;
    });
  }

  ngOnInit() {
    //console.log("Fetching profile");
    //this.getProfile("Hans");
  }

}
