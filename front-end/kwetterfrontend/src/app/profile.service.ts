import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class ProfileService {

  private getOwnKweetsUrl = "http://localhost:8080/kwetter/api/profile/ownkweets";
  private getFollowersUrl = "http://localhost:8080/kwetter/api/profile/followers";
  private getFollowingUrl = "http://localhost:8080/kwetter/api/profile/following";
  private getProfileDataUrl = "http://localhost:8080/kwetter/api/profile";
  private getMostRecentURL = "http://localhost:8080/kwetter/api/profile/mostrecent";
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



}
