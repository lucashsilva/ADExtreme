import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Router } from '@angular/router';
import { Observable} from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class AuthenticationService {
  authenticatedUser: AuthenticatedUser;
  APIUrl = "http://localhost:8080/api";

  constructor(private http: Http, private router: Router) {
    this.authenticatedUser = <AuthenticatedUser> JSON.parse(localStorage.getItem('currentUser'));
   }

  login(userCredentials: UserCredentials) {
    return this.http.post(this.APIUrl + "/auth/login", JSON.stringify(userCredentials), this.getOptions())
                      .map((response: Response) => {

       if(response.status >= 200) {
         let data = response.json();

         this.setAuthenticatedUser(<AuthenticatedUser> data);
        return true;
      } else {
        throw new Error("Credenciais inválidas.");
      }

    }).toPromise();
  }

  getUserInfo() {
    return this.http.get(this.APIUrl + "/users/")
  }

  isLoggedIn(): boolean {
    return this.authenticatedUser != null;
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.authenticatedUser = null;
    location.reload();
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

  private setAuthenticatedUser(user: AuthenticatedUser) {
    if(user.token && user.userInfo ) {
      this.authenticatedUser = user;
      localStorage.setItem('currentUser', JSON.stringify(this.authenticatedUser));
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

export class UserInfo {

  name: Name;
  password: string;
  role: string;
  email: string;
  credit: number;

  constructor() {
    this.name = new Name();
    this.role = "NATURAL_PERSON"
  }
}

class AuthenticatedUser {
  token: string;
  authenticated: boolean;
  userInfo: UserInfo;

  constructor() {
  }
}

export class Name {
  firstName: string;
  lastName: string;

  constructor() {  }
}

export class UserCredentials {
  email: string;
  password: string;

  constructor() {}

}
