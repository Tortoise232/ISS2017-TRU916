/**
 * Created by tudor on 06-May-17.
 */
import {Injectable} from '@angular/core';
import {Http, Response, Headers} from "@angular/http";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {Conference} from "./conference.model";

@Injectable()
export class ConferenceService {
  private conferenceUrl = 'http://localhost:8080/api/registerconf';
  private conferencesUrl = 'http://localhost:8080/api/conferences';
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

  private extractData(res: Response) {
    let body = res.json();
    console.log(body);
    return body.conferences || null;
  }

  private extractStatus(res: Response) {
    let status = res.status;
    return status;
  }

  register(name: string, date: string, deadline: string): Observable<number> {
    let ownerUsername = localStorage.getItem("user");
    let conference = {name, date, deadline, ownerUsername};
    return this.http
      .post(this.conferenceUrl, JSON.stringify({"conference": conference}), {headers: this.headers})
      .map(this.extractStatus)
      .catch(this.handleError);
  }

  getConference(name: string): Observable<Conference>{
    const url = `${this.conferencesUrl}/${name}`;
    return this.http
      .get(url, {headers: this.headers})
      .map((res: Response) => res.json())
  }

  findAll(): Observable<Conference[]> {
    return this.http
      .get('http://localhost:8080/api/listconf', {headers: this.headers})
      .map(this.extractData)
      .catch(this.handleError);
  }

  update(oldName: string, name: string, date: string, deadline: string): Observable<number>{
    let conference = {name, date, deadline};
    const url = `${this.conferencesUrl}/${oldName}`;
    return this.http
      .put(url, JSON.stringify({"conference": conference}), {headers: this.headers})
      .map(this.extractStatus)
      .catch(this.handleError);
  }

  addReviewer(conferenceName: string, userName: string): Observable<number>{
    const url = `${this.conferencesUrl}/${conferenceName}/add-reviewer`;
    return this.http
      .put(url, JSON.stringify(userName), {headers: this.headers})
      .map(this.extractStatus)
      .catch(this.handleError);
  }

  addAttender(conferenceName: string, userName: string): Observable<number>{
    const url = `${this.conferencesUrl}/${conferenceName}/attend`;
    return this.http
      .put(url, JSON.stringify(userName), {headers: this.headers})
      .map(this.extractStatus)
      .catch(this.handleError);
  }
}
