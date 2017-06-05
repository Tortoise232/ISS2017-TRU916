/**
 * Created by Laura on 6/2/2017.
 */
import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";

import 'rxjs/add/operator/switchMap';
import {Conference} from "../shared/conference.model";
import {ConferenceService} from "../shared/conference.service";
import {User} from "../../user/shared/user.model";

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
              private router: Router) {
  }

  ngOnInit(): void{
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

  update(name, date, deadline): void {
    this.doNotDisplay()
    if (name == "" || date == "" || deadline == "") {
      let required = document.getElementById("required");
      required.style.display = "block";
    }
    else {
      this.route.params
        .switchMap((params: Params) => this.conferenceService.update(params['name'], name, date, deadline))
        .subscribe(s => {
          if(s == 201){
            this.conference.name = name;
            this.router.navigate(['conferences/' + name]);
          }
          this.showUpdateStatus(s);
        });
    }
  }

  showUpdateStatus(status: number): void {
    if (status == 201) {
      let success = document.getElementById("success");
      success.style.display = "block";
      return;
    }
    if (status == 226) {
      let failure = document.getElementById("failure");
      failure.style.display = "block";
    }
  }

  doNotDisplay(): void{
    let success = document.getElementById("success");
    let failure = document.getElementById("failure");
    let required = document.getElementById("required");
    success.style.display = "none";
    failure.style.display = "none";
    required.style.display = "none";
  }
}
