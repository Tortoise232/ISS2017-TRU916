import {Component, HostListener} from "@angular/core";
import {Location} from '@angular/common';
import {AuthenticationService} from "../shared/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'login',
  providers: [AuthenticationService],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent{
  constructor(private location: Location,
              private router: Router,
              private authenticationService: AuthenticationService) {}

  goBack(): void {
    this.location.back();
  }

  gotoRegister(): void {
    this.router.navigate(['register']);
  }

  login(username, password): void{
    let failure = document.getElementById("failure");
    failure.style.display = "none";
    if (username == "" || password == "") {
      failure.style.display = "block";
    }

    let encodedPassword = btoa(password);
    this.authenticationService.login(username, encodedPassword);

    let failureUserPass = document.getElementById("failureUserPass");
    failureUserPass.style.display = "none";
    if(localStorage.getItem("user") == null) {
      failureUserPass.style.display = "block";
    }
  }

  eventHandler(keyCode,username,password) {
    if (keyCode == 13) {
      this.login(username, password);
    }
  }
}
