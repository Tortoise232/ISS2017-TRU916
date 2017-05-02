import {Injectable} from '@angular/core';
import {Http, Response, Headers} from "@angular/http";

import {User} from "./user.model";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class UserService {
    private registerUrl = 'http://localhost:8080/api/register';
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) {
    }

    private extractData(res: Response) {
        let body = res.json();
        return body.users || {};
    }

    private handleError(error: Response | any) {
        console.log("errrrrrrrrrrrrrrrrr");
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

    register(name: string, password: string, username: string, email:string): Observable<User> {
        let user = {name, password, username, email};
        return this.http
            .post(this.registerUrl, JSON.stringify({"user": user}), {headers: this.headers})
            .map(this.extractResponse)
            .catch(this.handleError);
    }
}
