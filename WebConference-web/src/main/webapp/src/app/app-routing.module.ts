import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {RegisterUserComponent} from "./user/user-register/user-register.component";
import {RegisterConferenceComponent} from "./conference/conference-register/conference-register.component";

const routes: Routes = [
    { path: 'home', component: HomeComponent},
    { path: 'login', component: LoginComponent},
    { path: 'register', component: RegisterUserComponent},
    { path: 'registerconf', component: RegisterConferenceComponent}
];
@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
