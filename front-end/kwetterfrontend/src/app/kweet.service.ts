import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import "rxjs/add/operator/catch";
import {Observable} from "rxjs/Observable";


@Injectable()
export class KweetService {

  private postKweetUrl = "http://localhost:8080/kwetter/api/kweet";

  constructor(protected httpClient: HttpClient) { }

  public postKweet(message: String, loggedInUser:string)
  {
    //console.log("bericht: " , message);
    //console.log("Account: " , loggedInUser);
    return this.httpClient.post(`${this.postKweetUrl}`, {
	    message: message,
	    username: loggedInUser
    }).catch(this.handleError);
  }


  private handleError(error: Response) {
    console.log(error);
    return Observable.throw(error);
  }
}
