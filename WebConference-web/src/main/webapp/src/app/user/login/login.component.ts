import {Component} from "@angular/core";
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
    let encodedPassword = btoa(password);
    this.authenticationService.login(username, encodedPassword);
  }
}
