import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Router } from '@angular/router';
import { Observable} from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class AuthenticationService {
  authenticatedUser: User;
  APIUrl = "http://localhost:8080/api";

  constructor(private http: Http) {
    this.authenticatedUser = <User> JSON.parse(localStorage.getItem('currentUser'));
   }

  login(userCredentials: UserCredentials) { 
    return this.http.post(this.APIUrl + "/login", JSON.stringify(userCredentials), this.getOptions())
                      .map((response: Response) => {

       if(response.status >= 200) {
         let data = response.json();

         this.setAuthenticatedUser(<User> data);
        return true;
      } else {
        throw new Error("Credenciais inválidas.");
      }

    }).toPromise();
  }

  isLoggedIn(): boolean {
    return this.authenticatedUser != null;
  }

  signup(userInfo: UserInfo) {
    return this.http.post(this.APIUrl + '/users', JSON.stringify(userInfo), this.getOptions()).map(res => {
      if(res.status == 201) {
        return this.login(userInfo).then(success => {
          return success;
        });
      }
    }).toPromise();
  }

  private setAuthenticatedUser(user: User) {
    if(user.token && user.email) {
      this.authenticatedUser = user;
      localStorage.setItem('currentUser', JSON.stringify({email: this.authenticatedUser.email,
                                                              token: this.authenticatedUser.token}));
    }
  }

  getOptions() {
  let options = new RequestOptions();
  let headers = new Headers();
  headers.append('Content-Type', 'application/json');
  
  if(this.authenticatedUser) {
    headers.append('Authorization', this.authenticatedUser.token);
  }

  options.headers = headers;

  return options;
  }
}

class User {
  name: string;
  email: string;
  token: string;
  role: string;

  constructor() {
    this.role = "USER";
  }
}

export class UserInfo extends User {
  password: string;

  constructor() {
    super();
  }
}

export class UserCredentials {
  email: string;
  password: string;

  constructor() {}

}

