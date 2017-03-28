import { Component, OnInit, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { Advertisement } from '../models/advertisement';
import { AdvertisementService } from '../services/advertisement.service';


@Component({
  selector: 'app-advertisement-card',
  templateUrl: './advertisement-card.component.html',
  styleUrls: ['./advertisement-card.component.scss']
})
export class AdvertisementCardComponent implements OnInit {
  advertisements: Array<Advertisement>;

  constructor(private advertisementService: AdvertisementService, private router: Router) {
    this.advertisements = new Array<Advertisement>();
  }

  ngOnInit() {
    this.advertisementService.getAllAds().then(res => {
      this.advertisements = res;
    });
  }


}
