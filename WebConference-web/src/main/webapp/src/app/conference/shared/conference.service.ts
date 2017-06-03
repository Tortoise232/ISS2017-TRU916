/**
 * Created by tudor on 06-May-17.
 */
import {Injectable} from '@angular/core';
import {Http, Response, Headers} from "@angular/http";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {HttpResponse} from "selenium-webdriver/http";

@Injectable()
export class ConferenceService {
  private conferenceUrl = 'http://localhost:8080/api/registerconf';
  private headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});

  constructor(private http: Http) {
  }

  private handleError(error: Response | any) {
    console.log("error");
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

  private extractResponse(res: Response) {
    let body = res.json();
    return body.response || {};
  }

  private extractStatus(res: Response) {
    let status = res.status;
    console.log(status);
    return status;
  }

  private extractData(res: Response) {
    let body = res.json();
    console.log(body);
    return body;
  }

  register(name: string, date: string, deadline: string): Observable<number> {
    var ownerUsername = localStorage.getItem("user");
    let conference = {name, date, deadline, ownerUsername};
    return this.http
      .post(this.conferenceUrl, JSON.stringify({"conference": conference}), {headers: this.headers})
      .map(this.extractStatus)
      .catch(this.handleError);
  }

  findAll(): Observable<string> {
    return this.http
      .get('http://localhost:8080/api/listconf', {headers: this.headers})
      .map(this.extractData)
      .catch(this.handleError);
  }
}
