import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs/Rx';
import { WebsocketService } from './websocket.service';

const CHAT_URL = 'ws://localhost:8080/kwetter/kweetsws';

export interface Message {
  profilename: string,
  message: string,
  postdate: string
}

@Injectable()
export class KweetwsService {

  public messages: Subject<Message>;

  constructor(wsService: WebsocketService) {
    this.messages = <Subject<any>>wsService
      .connect(CHAT_URL)
      .map((response: MessageEvent): Message => {
        console.log(response.data);

        let data = JSON.parse(response.data);
        return {
          profilename: data.owner.username,
          message: data.message,
          postdate: new Date(data.postDate).toLocaleDateString()
        }
        //return response.data;
      });
  }

}
