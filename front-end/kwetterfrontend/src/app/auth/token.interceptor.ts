import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders
} from '@angular/common/http';
import { ProfileService } from '../profile.service';
import { Observable } from 'rxjs/Observable';
import {RequestOptionsArgs} from "@angular/http";



@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(public auth: ProfileService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.auth.getToken();

    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': "Bearer " + token
    });

    if (token) {
      headers.append('Authorization', 'Bearer ' + this.auth.getToken().toString());
    }
    const cloneReq = request.clone({headers});

    return next.handle(cloneReq);
    //return next.handle(request);
  }
}
