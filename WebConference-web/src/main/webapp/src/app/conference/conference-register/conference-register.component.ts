/**
 * Created by tudor on 06-May-17.
 */
import {Component} from "@angular/core";
import {Location} from '@angular/common';
import {Router} from "@angular/router";

import {ConferenceService} from "../shared/conference.service";

@Component({
  moduleId: module.id,
  selector: 'register',
  templateUrl: './conference-register.component.html',
  styleUrls: ['./conference-register.component.css'],
})

export class RegisterConferenceComponent {
  constructor(private conferenceService: ConferenceService,
              private location: Location,
              private router: Router) {
  }

  goBack(): void {
    this.location.back();
  }

  showRegistrationStatus(status: string): void{
    if(status == "Created"){
      let success = document.getElementById("success");
      //let form = document.getElementById("form");
      //form.style.display = "none";
      success.style.display = "block";
    }
    if(status == "IM Used"){
      let failure = document.getElementById("failure");
      failure.style.display = "block";
    }
  }

  register(name, date, deadline): void {
    console.log("register");

    let required = document.getElementById("required");
    let invalidDate = document.getElementById("invalid-date");
    let success = document.getElementById("success");
    let failure = document.getElementById("failure");
    required.style.display = "none";
    invalidDate.style.display = "none";
    success.style.display = "none";
    failure.style.display = "none";

    if (!name || !date || !deadline) {
      console.log("All fields are required!");
      required.style.display = "block";
      return;
    }

    /*
    let datePattern = /^[0-3]?[0-9]\/[01]?[0-9]\/[12][90][0-9][0-9]$/;
    if(!datePattern.test(date)) {
      console.log("Invalid date format used!");
      invalidDate.style.display = "block";
      return;
    }
  */
    this.conferenceService.register(name, date, deadline)
      .subscribe(s => this.showRegistrationStatus(s))
  }
}
