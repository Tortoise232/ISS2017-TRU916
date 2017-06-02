/**
 * Created by tudor on 06-May-17.
 */
import {Component, OnInit} from "@angular/core";
import {Location} from '@angular/common';
import {Router} from "@angular/router";

import {ConferenceService} from "../shared/conference.service";
import {delay} from "rxjs/operator/delay";

@Component({
  moduleId: module.id,
  selector: 'registerconf',
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

  showRegistrationStatus(status: number): void{
    if(status == 201) {
      let success = document.getElementById("success");
      // let form = document.getElementById("register-form");
      // form.style.display = "none";
      success.style.display = "block";
      setTimeout(() => {
          this.router.navigate(['/home']);
        },
        2000);


    }
    if(status == 226){
      let failure = document.getElementById("failure");
      failure.style.display = "block";
    }
  }

  register(name, date,deadline): void {
    console.log("register");

    let required = document.getElementById("required");
    let invalidDate = document.getElementById("invalid-date");
    let success = document.getElementById("success");
    let failure = document.getElementById("failure");
    let deadLineWrong = document.getElementById("deadLineWrong");

    required.style.display = "none";
    invalidDate.style.display = "none";
    success.style.display = "none";
    failure.style.display = "none";
    deadLineWrong.style.display = "none";

    if (!name || !date) {
      console.log("All fields are required!");
      required.style.display = "block";
      return;
    }

    if (date.valueOf() >= deadline.valueOf()) {
      deadLineWrong.style.display = "block";
      return;
    }

    //
    // let datePattern = /^[0-3]?[0-9]\/[01]?[0-9]\/[12][90][0-9][0-9]$/;
    // if(!datePattern.test(date)) {
    //   console.log("Invalid date format used!");
    //   invalidDate.style.display = "block";
    //   return;
    // }

    this.conferenceService.register(name, date,deadline)
      .subscribe(s => this.showRegistrationStatus(s));

  }
}
