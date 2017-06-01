import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {AuthenticationService} from "../user/shared/authentication.service";

@Component({
  selector: 'home',
  providers: [AuthenticationService],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  private current:string ="/";
  private acti:string="bhome"
  constructor(private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.authenticationService.checkCredentials();
  }

  logout() {
    this.authenticationService.logout();
  }

  gotoRegisterConference(): void {
    this.router.navigate(['registerconf']);
   // let varr = document.getElementById("registerconf");
   // varr.style.display="block";
  }

  goTo(next:string,activv:string)
  {
    if(this.current != "/") {
      let varr = document.getElementById(this.current);
      varr.style.display = "none";
    }
    else {
      let varr = document.getElementById('bhome');
      varr.classList.remove('active');
    }
    if(next === "/") {
      this.router.navigate(["/home"]);
      let varacti = document.getElementById(this.acti);
      varacti.classList.remove('active');
      let varr = document.getElementById("bhome");
      varr.classList.add("active");
      this.current="/";
      this.acti="bhome";

    }
    else {
      let varacti = document.getElementById(this.acti);
      varacti.classList.remove('active');
      this.current = next;
      let varr = document.getElementById(next);
      varr.style.display = "block";
      varacti = document.getElementById(activv);
      varacti.classList.add('active');
      this.acti = activv;
    }
  }
}
