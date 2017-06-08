/**
 * Created by Andrei Novac on 8/06/2017.
 */

import {Component, OnInit} from "@angular/core";
import {Location} from '@angular/common';
import {Router} from "@angular/router";

import {ConferenceService} from "../conference/shared/conference.service";
import {Conference} from "../conference/shared/conference.model";

import {UserService} from "../user/shared/user.service";
import {User} from "../user/shared/user.model";

import {AuthenticationService} from "../user/shared/authentication.service";

@Component({
  moduleId: module.id,
  selector: 'list',
  templateUrl: './welcomepage.component.html',
  styleUrls: ['./welcomepage.component.css'],

})

export class WelcomePageComponent implements  OnInit{
  conferences: Conference[];
  private user: User;
  constructor(private userService: UserService,
              private authenticationService: AuthenticationService,
              private conferenceService: ConferenceService,
              private location: Location,
              private router: Router) {
  }

  ngOnInit(): void {
    this.authenticationService.checkCredentials();
    this.getUser();
    this.listAll();
  }

  listAll(){
    this.conferenceService.findAll().subscribe(conferences=>this.conferences=conferences);
  }

  goToConfView(name){
    this.router.navigateByUrl("/conferences/" + name);
  }

  getUser() {
    let username=localStorage.getItem("user");
    this.userService.getUsersByName(username).subscribe((userr)=>{this.user=userr});
  }
}
