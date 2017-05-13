import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {AuthenticationService} from "../user/shared/authentication.service";

@Component({
  selector: 'home',
  providers: [AuthenticationService],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent{
  constructor(private router: Router,
              private authenticationService: AuthenticationService) {}

  ngOnInit(){
    this.authenticationService.checkCredentials();
  }

  logout() {
    this.authenticationService.logout();
  }

  gotoRegisterConference(): void {
    this.router.navigate(['registerconf']);
  }
}
