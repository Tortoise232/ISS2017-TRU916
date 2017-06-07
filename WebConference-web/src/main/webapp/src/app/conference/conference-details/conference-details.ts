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
  currentUser: string;

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
    this.doNotDisplayMessagesForOwner()
    if (name == "" || date == "" || deadline == "") {
      let required = document.getElementById("update-required");
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

  addReviewer(userName): void {
    this.doNotDisplayMessagesForOwner();
    if (userName == "") {
      let required = document.getElementById("add-reviewer-required");
      required.style.display = "block";
    }
    else {
      this.route.params
        .switchMap((params: Params) => this.conferenceService.addReviewer(params['name'], userName))
        .subscribe(s => this.showAddReviewerStatus(s, userName));
    }
  }

  attend(): void {
    this.doNotDisplayMessagesForUser();
    this.route.params
        .switchMap((params: Params) => this.conferenceService.addAttender(params['name'], this.currentUser))
        .subscribe(s => this.showAttendanceStatus(s));
  }

  showUpdateStatus(status: number): void {
    if (status == 201) {
      let success = document.getElementById("update-success");
      success.style.display = "block";
      return;
    }
    if (status == 226) {
      let failure = document.getElementById("update-failure");
      failure.style.display = "block";
    }
  }

  showAddReviewerStatus(status: number, userName: string): void {
    if (status == 201) {
      let success = document.getElementById("add-reviewer-success");
      success.style.display = "block";
      let reviewerList = document.getElementById("reviewer-list-owner");
      let li = document.createElement("li");
      let button = document.createElement("button");
      button.innerHTML = "✘";
      li.appendChild(document.createTextNode(userName));
      li.appendChild(button);
      reviewerList.appendChild(li);
      return;
    }
    if (status == 226) {
      let failure = document.getElementById("add-reviewer-failure");
      failure.style.display = "block";
    }
  }

  showAttendanceStatus(status: number): void{
    if (status == 201) {
      let success = document.getElementById("attend-success");
      success.style.display = "block";
      let attendanceList = document.getElementById("attender-list-user");
      let li = document.createElement("li");
      li.appendChild(document.createTextNode(this.currentUser));
      attendanceList.appendChild(li);
      return;
    }
    if (status == 226) {
      let failure = document.getElementById("attend-failure");
      failure.style.display = "block";
    }
  }

  doNotDisplayMessagesForOwner(): void{
    let updateSuccess = document.getElementById("update-success");
    let updateFailure = document.getElementById("update-failure");
    let updateRequired = document.getElementById("update-required");
    let addReviewerSuccess = document.getElementById("add-reviewer-success");
    let addReviewerFailure = document.getElementById("add-reviewer-failure");
    let addReviewerRequired = document.getElementById("add-reviewer-required");
    updateSuccess.style.display = "none";
    updateFailure.style.display = "none";
    updateRequired.style.display = "none";
    addReviewerSuccess.style.display = "none";
    addReviewerFailure.style.display = "none";
    addReviewerRequired.style.display = "none";
  }

  doNotDisplayMessagesForUser(): void{
    let attendanceSuccess = document.getElementById("attend-success");
    let attendanceFailure = document.getElementById("attend-failure");
    attendanceSuccess.style.display = "none";
    attendanceFailure.style.display = "none";
  }

  gotToSubmit()
  {
    this.router.navigateByUrl("/paperadd/" + this.conference.name);
  }
}
