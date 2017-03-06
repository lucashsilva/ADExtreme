import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {

  private credentials: UserCredentials;
  private errors: Array<string>;

  constructor() {
    this.errors = new Array<string>();
   }

  ngOnInit() {
    this.resetForm();
  }

  resetForm() {
    this.credentials = new UserCredentials();
  }

  login() {
    if(this.validate()) {
      // call for login
    }
  }

  validate() {
    this.errors = new Array<string>();
    let hasError = false;

    if(!this.credentials.email) {
      this.errors.push("Email inválido.");
      hasError = true;
    }
    if (!this.credentials.password || this.credentials.password.length < 8) {
      this.errors.push("A senha deve conter pelo menos 8 caracteres.");
      hasError = true;
    }

    return hasError;
  }

}

class UserCredentials {
  email: string;
  password: string;
  constructor() {}
}