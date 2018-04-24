import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {tokenNotExpired} from "angular2-jwt";
import 'rxjs/add/operator/map';
import {toPromise} from "rxjs/operator/toPromise";
import {Router} from "@angular/router";

@Injectable()
export class ProfileService {

  private loggedInUser:string;

  private getOwnKweetsUrl = "http://localhost:8080/kwetter/api/profile/ownkweets";
  private getFollowersUrl = "http://localhost:8080/kwetter/api/profile/followers";
  private getFollowingUrl = "http://localhost:8080/kwetter/api/profile/following";
  private getProfileDataUrl = "http://localhost:8080/kwetter/api/profile";
  private getMostRecentURL = "http://localhost:8080/kwetter/api/profile/mostrecent";
  private getTimelineURL = "http://localhost:8080/kwetter/api/profile/timeline";
  private postLoginURL = "http://localhost:8080/kwetter/api/profile/login";
  private getAllProfilesURL = "http://localhost:8080/kwetter/api/profile/";
  private followProfileURL = "http://localhost:8080/kwetter/api/profile/follow";
  private headers = {'Content-Type': 'application/x-www-form-urlencoded'};

  public token: string;

  constructor(protected httpClient: HttpClient, private router: Router) {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }

  public getOwnKweets(profilename: String)
  {
    return this.httpClient.get(`${this.getOwnKweetsUrl}/${profilename}`);
  }

  public getFollowers(profilename: String)
  {
    return this.httpClient.get(`${this.getFollowersUrl}/${profilename}`);
  }

  public getFollowing(profilename: String)
  {
    return this.httpClient.get(`${this.getFollowingUrl}/${profilename}`);
  }

  public getProfileData(profilename: String)
  {
    return this.httpClient.get(`${this.getProfileDataUrl}/${profilename}`)
  }

  public getMostRecentKweet(loggedInUser:string)
  {
    return this.httpClient.get(`${this.getMostRecentURL}/${loggedInUser}`);
  }


  public getOwnTimeline(username: string) {
    return this.httpClient.get(`${this.getTimelineURL}/${username}`);
  }

  public login(login:string, password:string){
    //TODO: shareReplay() to prevent the receiver of this Observable from accidentally triggering multiple POST requests due to multiple subscriptions.

    let body = `login=${login}&password=${password}`;
    return this.httpClient.post(`${this.postLoginURL}`, body, {headers: this.headers, observe:'response'}).subscribe((res) => {
      // login successful if there's a jwt token in the response
      console.log("res", res);
      console.log("status", res.headers.get("status") );
      console.log("body", res.body);
      let status:number = +res.headers.get("status");
      //let token =  res.headers.get('AUTHORIZATION');
      let token:string =  res.body.toString();
      console.log("token", token);
      if(token != null)
      {
        this.token = token;
        localStorage.setItem('currentUser', JSON.stringify({ username: login, token: token }));
        localStorage.setItem('loggedinuser', login);
        localStorage.setItem('token', token);
        console.log("logged in");
        this.router.navigateByUrl('/home/'+ this.getLoggedInUser());
        return true;
      }
      if(status == 401)
      {
        console.log("Not logged in");
        this.router.navigateByUrl('/login/');
        return false;
      }
      else {
        console.log("Not logged in");
        this.router.navigateByUrl('/login/');
        return false;
      }
    });
  }

  public getLoggedInUser(){
    return  localStorage.getItem('loggedinuser');
  }
  public setLoggedInUser(loggedInUser){
    this.loggedInUser = loggedInUser;
  }

  public getAllProfiles(){
    return this.httpClient.get(`${this.getAllProfilesURL}`);
  }

  public follow(profileToFollow:string){
    //TODO: Actually send LoggedinUser
    let body = {username: localStorage.getItem("loggedinuser")};
    console.log(body);
    return this.httpClient.post(`${this.followProfileURL}/${profileToFollow}`, body);
  }

  public getToken(): string {
    return localStorage.getItem('token');
  }

  public isAuthenticated(): boolean {
    // get the token
    const token = this.getToken();
    // return a boolean reflecting
    // whether or not the token is expired
    return tokenNotExpired(null, token);
  }
}
