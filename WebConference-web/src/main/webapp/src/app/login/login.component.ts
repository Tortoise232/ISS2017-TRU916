import {Component} from "@angular/core";
import {Location} from '@angular/common';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent{
  constructor(private location: Location) {}

  goBack(): void {
    this.location.back();
  }

  login(){

  }
}
