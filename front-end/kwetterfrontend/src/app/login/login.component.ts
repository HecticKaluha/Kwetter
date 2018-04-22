import { Component, OnInit } from '@angular/core';
import {ProfileService} from "../profile.service";
import {FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  form:FormGroup;

  constructor(private fb:FormBuilder,
              private profileservice: ProfileService,
              private router: Router) {
    this.form = this.fb.group({
      email: ['',Validators.required],
      password: ['',Validators.required]
    });
  }

  ngOnInit() {
  }

  login() {
    const val = this.form.value;

    if (val.email && val.password) {
      this.profileservice.login(val.email, val.password)
        .subscribe(
          () => {
            console.log("User is logged in");
            this.router.navigateByUrl('/home/'+ val.email);
          }
        );
    }
  }

}
