import {Component} from "@angular/core";
import {Location} from '@angular/common';
import {Router} from "@angular/router";

import {UserService} from "../shared/user.service";

@Component({
    moduleId: module.id,
    selector: 'register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css'],
})

export class RegisterComponent {
  constructor(private userService: UserService,
              private location: Location,
              private router: Router) {
  }

  goBack(): void {
    this.location.back();
  }

  gotoLogin(): void {
    this.router.navigate(['login']);
  }

  showRegistrationStatus(status: number): void{
    if(status == 201){
      let success = document.getElementById("success");
      // let form = document.getElementById("container");
      // form.style.display = "none";
      success.style.display = "block";
      setTimeout(() => {
          this.router.navigate(['/login']);
        },
        2000);
      console.log("I'm CREATED");
      return;
    }
    if(status == 226){
      let failure = document.getElementById("failure");
      failure.style.display = "block";
      console.log("I'm Used");
    }
  }

  register(name, username, email, password): void {
    console.log("register");

    let required = document.getElementById("required");
    let invalidEmail = document.getElementById("invalid-email");
    let invalidPassword = document.getElementById("invalid-password");
    let success = document.getElementById("success");
    let failure = document.getElementById("failure");
    let failureAll = document.getElementById("failureAll");

    invalidEmail.style.display = "none";
    invalidPassword.style.display = "none";
    success.style.display = "none";
    failure.style.display = "none";
    failureAll.style.display = "none";

    if (!name || !password || !username || !email) {
      failureAll.style.display = "block";
      console.log("All fields are required!");
      return;
    }

    let emailPattern = /\S+@\S+\.\S+/;
    if(!emailPattern.test(email)) {
      console.log("Invalid email!");
      invalidEmail.style.display = "block";
      return;
    }

    let minPasswordLength = 6;
    if(password.length < minPasswordLength){
      console.log("Invalid password!");
      invalidPassword.style.display = "block";
      return;
    }

    let encodedPassword = btoa(password);
    this.userService.register(name, username, email, encodedPassword)
      .subscribe(s => this.showRegistrationStatus(s))
  }

  eventHandler(keyCode, name, username, email, password) {
    if (keyCode == 13) {
      this.register(name, username, email, password);
    }
  }
}
