import {Component} from "@angular/core";
import {Location} from '@angular/common';

import {UserService} from "../shared/user.service";


@Component({
    moduleId: module.id,
    selector: 'register',
    templateUrl: './user-register.component.html',
    styleUrls: ['./user-register.component.css'],
})
export class RegisterUserComponent {
    constructor(private userService: UserService,
                private location: Location) {
    }

    goBack(): void {
        this.location.back();
    }

    register(name, password, username, email): void {
        /*
        console.log("register");
        if (!name || !password || !username || !email) {
            console.log("all fields are required");
            alert("All fields are required!");
            return;
        }
        */
        var encodedPassword = btoa(password);
        this.userService.register(name, encodedPassword, username, email)
            .subscribe(_ => this.goBack());
    }
}
