import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppRoutingModule} from "./app-routing.module";

import {AppComponent} from "./app.component";
import {RegisterComponent} from "./user/register/register.component";

import {UserService} from "./user/shared/user.service";
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./user/login/login.component";
import {RegisterConferenceComponent} from "./conference/conference-register/conference-register.component";
import {ListConferenceComponent} from "./conference/conference-list/conference-list.component";
import {ConferenceService} from "./conference/shared/conference.service";
import {AuthenticationService} from "./user/shared/authentication.service";
import {ProfileComponent} from "./user/profile/profile.component";
import {ConferenceDetailsComponent} from "./conference/conference-details/conference-details";
import {PaperService} from "./paper/shared/paper.service";
import {PaperAdd} from "./paper/paper-add/paper-add.component";

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    RegisterConferenceComponent,
    ListConferenceComponent,
    HomeComponent,
    LoginComponent,
    ProfileComponent,
    ConferenceDetailsComponent,
    PaperAdd
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [UserService,AuthenticationService,ConferenceService, PaperService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
