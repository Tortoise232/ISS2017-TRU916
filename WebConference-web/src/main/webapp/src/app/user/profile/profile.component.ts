import {Component, OnInit} from "@angular/core";
import {Location} from '@angular/common';
import {Router} from "@angular/router";

import {UserService} from "../shared/user.service";
import {User} from "../shared/user.model";

@Component({
    moduleId: module.id,
    selector: 'profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css'],
})

export class ProfileComponent implements OnInit{

  private user:User;

  ngOnInit(): void {
    this.getUser();
  }

  constructor(private userService: UserService,
              private location: Location,
              private router: Router) {}

  goBack(): void {
    this.location.back();
  }

  // gotoLogin(): void {
  //   this.router.navigate(['login']);
  // }

  getUser() {
    let username=localStorage.getItem("user");
    this.userService.getUsersByName(username).subscribe((userr)=>{this.user=userr});
    // console.log(this.username);
  }
}
