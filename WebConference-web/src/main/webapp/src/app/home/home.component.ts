import {Component} from "@angular/core";
import {Router} from "@angular/router";

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent{
  constructor(private router: Router) {}

  gotoLogin(): void {
    this.router.navigate(['login']);
  }

  gotoRegister(): void {
    this.router.navigate(['register']);
  }

  gotoRegisterConference(): void {
    this.router.navigate(['registerconf']);
  }
}
