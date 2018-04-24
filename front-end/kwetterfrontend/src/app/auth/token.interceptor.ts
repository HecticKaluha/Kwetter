import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { ProfileService } from '../profile.service';
import { Observable } from 'rxjs/Observable';
import {RequestOptionsArgs} from "@angular/http";



@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(public auth: ProfileService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    request = request.clone({
      setHeaders: {
        Authorization: `${this.auth.getToken()}`
      }
    });
    return next.handle(request);
  }
}
