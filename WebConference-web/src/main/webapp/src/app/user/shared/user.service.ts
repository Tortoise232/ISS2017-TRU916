import {Injectable} from '@angular/core';
import {Http, Response, Headers} from "@angular/http";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {
  private registerUrl = 'http://localhost:8080/api/register';
  private authenticationUrl = 'http://localhost:8080/api/authenticate';
  private headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});

  constructor(private http: Http) {}

  private extractResponse(res: Response) {
    let body = res.json();
    return body.response || {};
  }

  private extractStatus(res: Response){
    let status = res.statusText;
    console.log(status);
    return status;
  }

  register(name: string, username: string, email:string, password: string): Observable<string> {
    let user = {name, password, username, email};
    return this.http
      .post(this.registerUrl, JSON.stringify({"user": user}), {headers: this.headers})
      .map(this.extractStatus);
  }

  authenticate(username: string, password: string): Observable<string>{
    let name = "";
    let email = "";
    let user = {name, password, username, email};
    return this.http
      .post(this.authenticationUrl, JSON.stringify({"user": user}), {headers: this.headers})
      .map(this.extractStatus)
  }
}
