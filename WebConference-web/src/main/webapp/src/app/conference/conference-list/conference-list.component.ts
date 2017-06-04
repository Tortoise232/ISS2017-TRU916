/**
 * Created by Petean Mihai on 5/27/2017.
 */


import {Component, OnInit} from "@angular/core";
import {Location} from '@angular/common';
import {Router} from "@angular/router";

import {ConferenceService} from "../shared/conference.service";
import {Conference} from "../shared/conference.model";

@Component({
  moduleId: module.id,
  selector: 'list',
  templateUrl: './conference-list.component.html',
  styleUrls: ['./conference-list.component.css'],

})

export class ListConferenceComponent implements  OnInit{
  conferences: Conference[];
  constructor(private conferenceService: ConferenceService,
              private location: Location,
              private router: Router,) {
  }

  ngOnInit(): void {
    this.listAll();
  }

  listAll(){
    this.conferenceService.findAll().subscribe(conferences=>this.conferences=conferences);
  }

  goToConfView(name){
    this.router.navigateByUrl("/conferences/" + name);
  }
}

