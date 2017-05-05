import {Component} from "@angular/core";
import {Location} from '@angular/common';
import {Router} from "@angular/router";

import {UserService} from "../shared/user.service";

@Component({
    moduleId: module.id,
    selector: 'register',
    templateUrl: './user-register.component.html',
    styleUrls: ['./user-register.component.css'],
})

export class RegisterUserComponent {
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

  showRegistrationStatus(status: string): void{
    if(status == "Created"){
      let success = document.getElementById("success");
      let form = document.getElementById("register-form");
      form.style.display = "none";
      success.style.display = "block";
    }
    if(status == "IM Used"){
      let failure = document.getElementById("failure");
      failure.style.display = "block";
    }
  }

  register(name, password, username, email): void {
    console.log("register");

    let required = document.getElementById("required");
    let invalidEmail = document.getElementById("invalid-email");
    let invalidPassword = document.getElementById("invalid-password");
    let success = document.getElementById("success");
    let failure = document.getElementById("failure");
    required.style.display = "none";
    invalidEmail.style.display = "none";
    invalidPassword.style.display = "none";
    success.style.display = "none";
    failure.style.display = "none";

    if (!name || !password || !username || !email) {
      console.log("All fields are required!");
      required.style.display = "block";
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
    this.userService.register(name, encodedPassword, username, email)
      .subscribe(s => this.showRegistrationStatus(s))
  }
}
