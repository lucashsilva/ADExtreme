import { Component, OnInit, Input } from '@angular/core';
import { Advertisement, FurnitureAdvertisement, JobAdvertisement, RealtyAdvertisement, ServiceAdvertisement } from '../models/advertisement';

import { AdvertisementService } from '../services/advertisement.service';

@Component({
  selector: 'app-advertisement',
  templateUrl: './advertisement.component.html',
  styleUrls: ['./advertisement.component.scss']
})
export class AdvertisementComponent implements OnInit {

  @Input() advertisement: Advertisement;

  constructor(private advertisementService: AdvertisementService) {
    this.advertisement = new Advertisement();
   }

  ngOnInit() {
  }

  buyAd(id) {
    this.advertisementService.buyAd(id).then(success => {
      if(success) {
        console.log("Advertisement successful bought");
      }
    });
  }
}
