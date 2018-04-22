import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

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
  private headers = {'Content-Type': 'application/x-www-form-urlencoded'};

  constructor(protected httpClient: HttpClient) { }

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
    console.log("Aangekomen")
    // let body = new URLSearchParams();
    // body.set('login', login);
    // body.set('password', password);
    let body = `login=${login}&password=${password}`;
    return this.httpClient.post(`${this.postLoginURL}`, body, {headers: this.headers});
  }

  public getLoggedInUser(){
    return this.loggedInUser;
  }
  public setLoggedInUser(loggedInUser){
    this.loggedInUser = loggedInUser;
  }
}
