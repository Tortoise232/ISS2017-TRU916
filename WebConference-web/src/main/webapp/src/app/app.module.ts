import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppRoutingModule} from "./app-routing.module";

import {AppComponent} from "./app.component";
import {RegisterUserComponent} from "./user/user-register/user-register.component";

import {UserService} from "./user/shared/user.service";
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {RegisterConferenceComponent} from "./conference/conference-register/conference-register.component";
import {ConferenceService} from "./conference/shared/conference.service";

@NgModule({
  declarations: [
    AppComponent,
    RegisterUserComponent,
    RegisterConferenceComponent,
    HomeComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [UserService,ConferenceService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
