import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService, UserInfo } from '../../services/authentication.service';
@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.scss']
})
export class SignupFormComponent implements OnInit {
  private userInfo: UserInfo;
  private errors: Array<string>;

  constructor(private authenticationService: AuthenticationService, private router: Router) {
    this.errors = new Array<string>();
   }

  ngOnInit() {
    this.resetForm();
  }

  resetForm() {
    this.userInfo = new UserInfo();
  }

  validate() {
    this.errors = new Array<string>();
    let hasError = false;

    if (!this.userInfo.name) {
      this.errors.push("Nome inválido.");
      hasError = true;
    }
    if(!this.userInfo.email) {
      this.errors.push("Email inválido.");
      hasError = true;
    }
    if (!this.userInfo.password || this.userInfo.password.length < 8) {
      this.errors.push("A senha deve conter pelo menos 8 caracteres.");
      hasError = true;
    }

    return !hasError;
  }

  signup() {
    if(this.validate()) {
      this.authenticationService.signup(this.userInfo).then(success => {
        if(success) {
          this.router.navigate(['dashboard']);
        } else {
          this.errors = new Array<string>();
          this.errors.push("Ocorreu um erro no cadastro. Tente novamente.");
        }
      });
    }
  }

}
