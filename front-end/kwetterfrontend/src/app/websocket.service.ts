import {Injectable} from '@angular/core';
import * as Rx from 'rxjs/Rx';

@Injectable()
export class WebsocketService {

  ws:WebSocket;
  obj:any;

  constructor() { }

  private subject: Rx.Subject<MessageEvent>;

  public connect(url): Rx.Subject<MessageEvent> {
    if (!this.subject) {
      this.subject = this.create(url);
      console.log("Successfully connected: " + url);
    }
    return this.subject;
  }

  private create(url): Rx.Subject<MessageEvent> {
    this.ws = new WebSocket(url);

    let observable = Rx.Observable.create(
      (obs: Rx.Observer<MessageEvent>) => {
        this.ws.onmessage = obs.next.bind(obs);
        this.ws.onerror = obs.error.bind(obs);
        this.ws.onclose = obs.complete.bind(obs);
        return this.ws.close.bind(this.ws);
      });
    let observer = {
      next: (data: Object) => {
        if (this.ws.readyState === WebSocket.OPEN) {
          this.ws.send(JSON.stringify(data));
        }
      }
    };
    return Rx.Subject.create(observer, observable);
  }

  /*public sendMessage(message: string, loggedInUser: string) {
    console.log("aangekomen in sendmessage");
    if (this.ws.readyState === WebSocket.OPEN) {
      console.log("websocket is open.");
      this.obj =
        {
          "profilename": loggedInUser,
          "message": message
        };
      this.obj = JSON.stringify(this.obj);
      this.ws.send(this.obj);
      console.log("sent message");
    }
  }*/
}
