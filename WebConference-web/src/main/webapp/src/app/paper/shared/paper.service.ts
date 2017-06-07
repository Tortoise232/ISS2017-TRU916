/**
 * Created by cata on 05.06.2017.
 */


import {Injectable} from "@angular/core";
import {Http, Response, Headers, RequestOptions} from "@angular/http";
import {Observable} from "rxjs/Observable";

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class PaperService{
  private paperUrl='http://localhost:8080/api/paperadd';
  private paperUrlUpload='http://localhost:8080/api/create';
  private headers = new Headers({'Content-Type': 'application/json;charset=UTF-8'});

  constructor(private http:Http){}

  private extractResponse(res:Response)
  {
    let body =res.json();
    return body.response ||{};
  }
  private extractData(res:Response)
  {
    let body =res.json();
    console.log(res);
    return body.paper ||{};
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

}


