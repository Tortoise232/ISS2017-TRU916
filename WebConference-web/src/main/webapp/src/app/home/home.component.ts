import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {AuthenticationService} from "../user/shared/authentication.service";
import {Conference} from "../conference/shared/conference.model";
import {ConferenceService} from "../conference/shared/conference.service";

@Component({
  selector: 'home',
  providers: [AuthenticationService],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements  OnInit{
  conferences: Conference[];
  // private current:string ="/";
  // private acti:string="bhome";
  private currentUser:string;


  constructor(private conferenceService : ConferenceService,
              private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.authenticationService.checkCredentials();
    this.currentUser = localStorage.getItem("user");
    this.listAll();
  }

  logout() {
    this.authenticationService.logout();
  }

  listAll(){
    this.conferenceService.findAll().subscribe(conferences=>this.conferences=conferences);
  }

  goToConfView(name){
    this.router.navigateByUrl("/conferences/" + name);
  }

  goToPaperView(){
    this.router.navigateByUrl("/listpapersforuser/" + this.currentUser);
  }

  // loadPage(data:string) {
  //   let activePage = document.getElementById(this.acti);
  //   console.log(activePage);
  // }
  //
  // goTo(next:string,activv:string)
  // {
  //   if(this.current != "/") {
  //     let varr = document.getElementById(this.current);
  //     varr.style.display = "none";
  //   }
  //   else {
  //     let varr = document.getElementById('bhome');
  //     varr.classList.remove('active');
  //   }
  //   if(next === "/") {
  //     this.router.navigate(["/home"]);
  //     let varacti = document.getElementById(this.acti);
  //     varacti.classList.remove('active');
  //     let varr = document.getElementById("bhome");
  //     varr.classList.add("active");
  //     this.current="/";
  //     this.acti="bhome";
  //
  //   }
  //   else {
  //     let varacti = document.getElementById(this.acti);
  //     varacti.classList.remove('active');
  //     this.current = next;
  //     let varr = document.getElementById(next);
  //     varr.style.display = "block";
  //     varacti = document.getElementById(activv);
  //     varacti.classList.add('active');
  //     this.acti = activv;
  //   }
  // }
}
