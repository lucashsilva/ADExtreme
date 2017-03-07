import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthService {
  authToken: any;
  user: any;


  constructor(private http: Http) { }

  registerUser(user) {
      let header = new Headers();
      header.append('Content-Type', 'aplication/json');
      return this.http.post('http://localhost:8080/user/register', JSON.stringify(user), {headers: header}).map(res => res.json());
  }

}
