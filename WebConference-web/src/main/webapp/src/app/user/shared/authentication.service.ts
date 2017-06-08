/**
 * Created by Laura on 5/13/2017.
 */
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from "./user.service";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AuthenticationService {

  constructor(private router: Router,
              private userService: UserService){}

  logout() {
    console.log("logout");
    localStorage.removeItem("user");
    this.router.navigate(['login']);
  }

  showLoginStatus(status: number, username: string): void{
    console.log(status);
    if (status == 200){
      localStorage.setItem("user", username);
      this.router.navigate(['welcomepage']);
    }
    if (status == 401) {
    }
  }

  login(username: string, password: string): void{
    console.log("login");
    this.userService.authenticate(username, password)
      .subscribe(s => this.showLoginStatus(s, username));
  }

  checkCredentials(){
    console.log("checking credentials");
    if (localStorage.getItem("user") === null){
      console.log("not logged in");
      this.router.navigate(['login']);
    }
  }
}
