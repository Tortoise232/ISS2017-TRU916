/**
 * Created by Laura on 6/2/2017.
 */
import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';

import 'rxjs/add/operator/switchMap';
import {Conference} from "../shared/conference.model";
import {ConferenceService} from "../shared/conference.service";


@Component({
  selector: 'conference-details',
  templateUrl: './conference-details.component.html',
  styleUrls: ['./conference-details.component.css'],
})

export class ConferenceDetailsComponent implements OnInit {

  @Input()
  conference: Conference;

  constructor(private conferenceService: ConferenceService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void{
    this.getConference();
  }

  getConference() {
    this.route.params
      .switchMap((params: Params) => this.conferenceService.getConference(params['name']))
      .subscribe(conference => this.conference = conference);
  }
}
