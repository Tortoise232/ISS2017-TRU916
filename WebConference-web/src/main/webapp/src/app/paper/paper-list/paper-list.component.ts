/**
 * Created by tudor on 07-Jun-17.
 */


import {Component, OnInit, Input} from "@angular/core";
import {Location} from '@angular/common';
import {ActivatedRoute, Params, Router} from "@angular/router";

import {PaperService} from "../shared/paper.service";
import {Paper} from "../shared/paper.model";

@Component({
  moduleId: module.id,
  selector: 'paper-list',
  templateUrl: './paper-list.component.html',
  styleUrls: ['./paper-list.component.css'],

})

export class ListPaperComponent implements  OnInit{
  papers: Paper[];

  @Input()

  private currentUser:string;
  private conferenceName:string;

  constructor(private paperService: PaperService,
              private location: Location,
              private route: ActivatedRoute,
              private router: Router,) {
  }

  ngOnInit(): void {
    this.currentUser = localStorage.getItem("user");
    this.route.params
      .switchMap((params: Params) => this.conferenceName=params['name']).subscribe(_=>{});
    this.listAllFromConference(this.conferenceName);
  }

  listAll(){
    this.paperService.findAll().subscribe(papers=>this.papers=papers);
  }

  listAllFromConference(conferenceName){
    this.paperService.findAllFromConference(conferenceName).subscribe(papers=>this.papers=papers);
  }

  changePaperStatus(name){
    this.paperService.changePaperStatus(name);
    //this.listAllFromConference(this.conferenceName);
  }
}

