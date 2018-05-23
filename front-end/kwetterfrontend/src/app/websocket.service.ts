import {Injectable} from '@angular/core';

@Injectable()
export class WebsocketService {

  WS_URL = 'ws://localhost:8080/kwetter/kweetsws';
  ws: any;
  obj: any;
  observable: any;

  constructor() {
    this.ws = new WebSocket(this.WS_URL);

    this.ws.onmessage = function (kweet) {
      //add kweet to timeline
      console.log('from connection', kweet.data);
    };
  }

  receiveMessage() {

  }

  public sendMessage(message: string, loggedInUser: string) {
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
  }
}
