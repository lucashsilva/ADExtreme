import { Component, OnInit, Input } from '@angular/core';
import { Advertisement, FurnitureAdvertisement, JobAdvertisement, RealtyAdvertisement, ServiceAdvertisement } from '../models/advertisement';
import { Router } from '@angular/router';

import { AdvertisementService } from '../services/advertisement.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-advertisement',
  templateUrl: './advertisement.component.html',
  styleUrls: ['./advertisement.component.scss']
})
export class AdvertisementComponent implements OnInit {

  @Input() advertisement: Advertisement;

  constructor(private advertisementService: AdvertisementService, private authenticationService: AuthenticationService, private router: Router) {
    this.advertisement = new Advertisement();
   }

  ngOnInit() {
  }

  buyAd() {
    if(!this.authenticationService.isLoggedIn()) {
      alert("Você deve estar conectado para comprar no site.");
    } else {
      if(confirm("Você deseja comprar esse anúncio?")){
        this.advertisementService.buyAd(this.advertisement).then(success => {
          if(success) {
            alert("Compra efetuada com sucesso!")
            location.reload();
          }
        }).catch(error => {
          alert("Não foi possível efetuar a compra. " + error);
        });
      }
    }
  }
  detail(id) {
    this.router.navigate(['/advertisement-rating', id]);
  }
}
