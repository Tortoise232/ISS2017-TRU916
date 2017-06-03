/**
 * Created by Petean Mihai on 5/27/2017.
 */


import {Component} from "@angular/core";
import {Location} from '@angular/common';
import {Router} from "@angular/router";

import {ConferenceService} from "../shared/conference.service";

@Component({
  moduleId: module.id,
  selector: 'list',
  templateUrl: './conference-list.component.html',
  styleUrls: ['./conference-list.component.css'],
})

export class ListConferenceComponent {
  constructor(private conferenceService: ConferenceService,
              private location: Location,
              private router: Router) {
  }
  

  listAll(){
    let data;
    let result = document.getElementById("result");
    this.conferenceService.findAll().subscribe(conferences => data = JSON.stringify(conferences));
  }
}

