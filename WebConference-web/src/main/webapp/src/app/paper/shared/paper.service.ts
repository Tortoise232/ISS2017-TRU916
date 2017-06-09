/**
 * Created by cata on 05.06.2017.
 */


import {Injectable} from "@angular/core";
import {Http, Response, Headers, RequestOptions} from "@angular/http";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {Paper} from "./paper.model"

@Injectable()
export class PaperService{
  private paperUrl='http://localhost:8080/api/paperadd';
  private paperUrlUpload='http://localhost:8080/api/create';
  private papersUrl = 'http://localhost:8080/api/listpapers';
  private paperStatusChangeUrl= 'http://localhost:8080/api/paperchangestatus';
  private papersForUserUrl = 'http://localhost:8080/api/listpapersforuser';
  private headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});

  constructor(private http:Http){}

  private extractResponse(res:Response)
  {
    let body =res.json();
    return body.response ||{};
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

  private extractData(res:Response)
  {
    let body =res.json();
    console.log(res);
    return body.papers || null;
  }


  private extractStatus(res: Response){
    console.log("Status");
    let status = res.status;
    console.log(status);
    return status;
  }
  addPaper(conference:string, user:string,name:string,path:string):Observable<number>
  {
    let rating:number=0;
    let paper ={name,conference,path,rating,user};
    console.log("Service paper "+this.paperUrl);
    console.log(paper);
  return this.http.post(this.paperUrl,JSON.stringify({"paper":paper}),{headers:this.headers}).map(this.extractStatus);

  }

  changePaperStatus(paperName:string){
    this.http.post(this.paperStatusChangeUrl,JSON.stringify({"paperName":paperName}),{headers:this.headers});
  }

  create(data:FormData)
  {
    let headers = new Headers();
    headers.set('Accept', 'application/json');
    let options = new RequestOptions({headers: headers});

    this.http.post(this.paperUrlUpload, data, options)
        .map(res => res.json())
        .catch(error => Observable.throw(error))
        .subscribe(_=>console.log("Subsccribed"));

    }

  findAll(): Observable<Paper[]> {
    return this.http
      .get(`${this.papersUrl}`, {headers: this.headers})
      .map(this.extractData)
      .catch(this.handleError);
  }

  findAllFromConference(conferenceName): Observable<Paper[]>{
    return this.http
      .get(`${this.papersUrl}/${conferenceName}`, {headers: this.headers})
      .map(this.extractData)
      .catch(this.handleError);
  }

  findAllForUser(userName){
    return this.http
      .get(`${this.papersForUserUrl}/${userName}`, {headers: this.headers})
      .map(this.extractData)
      .catch(this.handleError);
  }

}


