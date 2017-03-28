import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Advertisement } from '../models/advertisement';
import { API_BASE_URL } from '../config/app-config';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class AdvertisementService {

  constructor(private authenticationService: AuthenticationService, private http: Http) { }

  getAllAds(): Promise<Advertisement[]> {
    return this.http.get(API_BASE_URL + "advertisements", this.authenticationService.getOptions()).map(res => {
      if(res.status >= 200) {
        return res.json();
      } else {
        throw new Error("Não foi possível obter os anúncios.");
      }
    }).toPromise();
  }
  
  createAds(ads: Advertisement): Promise<boolean> {
    return this.http.post(API_BASE_URL + "advertisements", JSON.stringify(ads), this.authenticationService.getOptions()).map(res =>{
      if(res.status >= 200) {
        return true;
      } else {
        throw new Error("Não foi possível cadastrar o anúncio.");
      }
    }).toPromise();
  }

}
