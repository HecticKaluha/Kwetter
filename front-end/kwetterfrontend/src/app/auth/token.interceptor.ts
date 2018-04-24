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
    const headers = new HttpHeaders({
      'Authorization': 'Bearer \'' + this.auth.getToken().toString() + '\'',
      'Content-Type': 'application/json'
    });
    console.log("headers zijn", headers);
    const cloneReq = request.clone({headers});

    return next.handle(cloneReq);
    //return next.handle(request);
  }
}
