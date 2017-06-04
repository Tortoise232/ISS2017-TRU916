/**
 * Created by Laura on 6/2/2017.
 */
import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";

import 'rxjs/add/operator/switchMap';
import {Conference} from "../shared/conference.model";
import {ConferenceService} from "../shared/conference.service";
import {User} from "../../user/shared/user.model";
import {AuthenticationService} from "../../user/shared/authentication.service";


@Component({
  selector: 'conference-details',
  templateUrl: './conference-details.component.html',
  styleUrls: ['./conference-details.component.css'],
})

export class ConferenceDetailsComponent implements OnInit {

  @Input()
  conference: Conference;
  reviewers: User[];
  attenders: User[];
  speakers: User[];
  owner: String;
  currentUser: String;

  constructor(private conferenceService: ConferenceService,
              private route: ActivatedRoute,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void{
    console.log("REACHED ME");
    this.authenticationService.checkCredentials();
    this.getConference();
    this.currentUser = localStorage.getItem("user");
  }

  getConference() {
    this.route.params
      .switchMap((params: Params) => this.conferenceService.getConference(params['name']))
      .subscribe(conference => {
        this.conference = conference;
        this.reviewers = conference.reviewers;
        this.attenders = conference.attenders;
        this.speakers = conference.speakers;
        this.owner = conference.ownerUsername;
        }
      );
  }
}
