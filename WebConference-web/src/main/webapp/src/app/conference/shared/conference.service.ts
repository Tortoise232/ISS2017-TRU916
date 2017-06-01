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

  constructor(private http: Http) {}

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

  private extractStatus(res: Response){
    let status = res.status;
    console.log(status);
    return status;
  }


  register(name: string, date: string): Observable<number> {
    let conference = {name, date};
    return this.http
      .post(this.conferenceUrl, JSON.stringify({"conference": conference}), {headers: this.headers})
      .map(this.extractStatus)
      .catch(this.handleError);
  }
}
