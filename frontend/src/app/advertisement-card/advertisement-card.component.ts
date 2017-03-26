import { Component, OnInit } from '@angular/core';
import { Advertisement } from '../models/advertisement';
import { AdvertisementService } from '../services/advertisement.service';


@Component({
  selector: 'app-advertisement-card',
  templateUrl: './advertisement-card.component.html',
  styleUrls: ['./advertisement-card.component.scss']
})
export class AdvertisementCardComponent implements OnInit {
  advertisements: Array<Advertisement>;

  constructor(private advertisementService: AdvertisementService) {
    this.advertisements = new Array<Advertisement>();
  }

  ngOnInit() {
    this.advertisementService.getAllAds().then(res => {
      this.advertisements = res;
    });
  }

}
