/**
 * Created by cata on 04.06.2017.
 */
import {PaperService} from "../shared/paper.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Component, Input, OnInit} from "@angular/core";

import {Location} from '@angular/common';
import {AuthenticationService} from "../../user/shared/authentication.service";
@Component({
  selector:'paper-add',
  templateUrl:'./paper-add.component.html',
  styleUrls:['./paper-add.component.css']
})

export class PaperAdd implements OnInit{

  @Input()

  private currentUser:string;
  private conferenceName:string;


  constructor(private location: Location,private route: ActivatedRoute, private router: Router, private paperService: PaperService,private authentification:AuthenticationService ) {

  }
  ngOnInit(): void {
    // throw new Error('Method not implemented.');
    this.authentification.checkCredentials();
    this.currentUser = localStorage.getItem("user");

  }


  goBack(): void {
    this.location.back();
  }

    showStatus(status:number)
    {
      if (status === 201)
      {
        alert("Uploaded");
        this.goBack();
      }
      if(status === 226)
      {
        alert("Problem at uploading");
      }
    }

  add(name, path) {
    if(name === "" || path === "" )
    {
      alert("All fields are mandatory!!");
      return;
    }
    this.route.params
      .switchMap((params: Params) => this.conferenceName=params['name']).subscribe(_=>{});
    // console.log(this.conferenceName);
    // console.log(name);
    // console.log(path);
    // console.log(this.currentUser);
    this.paperService.addPaper(this.conferenceName, this.currentUser,name, path).subscribe(s=>this.showStatus(s));

  }
}
