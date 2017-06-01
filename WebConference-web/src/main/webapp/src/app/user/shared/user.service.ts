///<reference path="../../../../node_modules/@angular/http/src/http.d.ts"/>
import {Injectable} from '@angular/core';
import {Http, Response, Headers} from "@angular/http";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {User} from "./user.model";

@Injectable()
export class UserService {
  private registerUrl = 'http://localhost:8080/api/register';
  private authenticationUrl = 'http://localhost:8080/api/authenticate';
  private findUser = 'http://localhost:8080/api/profile';
  private headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});

  constructor(private http: Http) {}

  private extractResponse(res: Response) {
    let body = res.json();
    return body.response || {};
  }

  private extractStatus(res: Response){
    let status = res.status;
    console.log(status);
    return status;
  }

  private extractData(res:Response)
  {
    let body = res.json();
    console.log(res);
    return body.user || {};
  }
  register(name: string, username: string, email:string, password: string): Observable<number> {
    let user = {name, password, username, email};
    return this.http
      .post(this.registerUrl, JSON.stringify({"user": user}), {headers: this.headers})
      .map(this.extractStatus);
  }

  authenticate(username: string, password: string): Observable<number>{
    let name = "";
    let email = "";
    let user = {name, password, username, email};
    return this.http
      .post(this.authenticationUrl, JSON.stringify({"user": user}), {headers: this.headers})
      .map(this.extractStatus)
  }

  getUsersByName(username: string): Observable<User>{
    let name = "";
    let email = "";
    let password="";
    let user = {name,username,email,password};
    // console.log(user);
    return this.http.
    post(this.findUser, JSON.stringify({"user": user}), {headers: this.headers})
      .map(this.extractData);
  }

}
