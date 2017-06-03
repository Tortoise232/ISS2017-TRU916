import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{
  constructor(private router: Router,private location:Location) {}

  ngOnInit() : void {
    if(this.location.path() == "")
      this.router.navigate(['home']);

  }
}

