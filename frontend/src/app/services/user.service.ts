import { Injectable } from '@angular/core';
import { Deposit } from '../models/deposit';
import { API_BASE_URL } from '../config/app-config';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { AuthenticationService } from './authentication.service';

@Injectable()
export class UserService {

  constructor(private http: Http, private authenticationService: AuthenticationService) { }

  deposit(deposit: Deposit): Promise<boolean> {
    return this.http.post(API_BASE_URL + 'users/credits/deposit', deposit, this.authenticationService.getOptions()).map(res => {
      return res.status == 200;
    }).toPromise();
  }

}
