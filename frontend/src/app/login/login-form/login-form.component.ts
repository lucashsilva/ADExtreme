import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { UserCredentials } from '../../models/user';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {

  private credentials: UserCredentials;
  private errors: Array<string>;

  constructor(private authenticationService: AuthenticationService, private router: Router) {
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
      this.authenticationService.login(this.credentials).then(success => {
        if(success) {
          this.router.navigate(['/dashboard']);
        }
      }).catch(err => {
        this.errors = ["Credenciais inválidas."];
      });
    }
  }

  validate() {
    this.errors = new Array<string>();
    let hasError = false;

    if(!this.credentials.email || !this.credentials.email.includes("@")) {
      this.errors.push("Email inválido.");
      hasError = true;
    }
    if (!this.credentials.password || this.credentials.password.length < 8) {
      this.errors.push("A senha deve conter pelo menos 8 caracteres.");
      hasError = true;
    }

    return !hasError;
  }

}
