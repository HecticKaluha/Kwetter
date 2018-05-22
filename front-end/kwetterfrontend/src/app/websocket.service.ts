import { Injectable } from '@angular/core';

@Injectable()
export class WebsocketService {

  WS_URL = 'ws://localhost:8080/kwetter/kweetsws';
  ws : any;
  observable: any;

  constructor() {
    this.ws = new WebSocket(this.WS_URL);

    this.ws.onmessage = function (kweet) {
      console.log('from connection', kweet.data);
    };
  }

  receiveMessage()
  {

  }

  sendMessage()
  {
    if (this.ws.readyState === WebSocket.OPEN) {
      console.log("sent message");
      this.ws.send("test");
    }
  }
}
