import { Component, OnInit, Input } from '@angular/core';
import { Advertisement, AdvertisementType, FurnitureAdvertisement, JobAdvertisement, RealtyAdvertisement, ServiceAdvertisement } from '../models/advertisement';

@Component({
  selector: 'app-advertisement',
  templateUrl: './advertisement.component.html',
  styleUrls: ['./advertisement.component.scss']
})
export class AdvertisementComponent implements OnInit {

  @Input() advertisement: Advertisement;

  constructor() {
    this.advertisement = new Advertisement();
   }

  ngOnInit() {
  }

  initialize(type: AdvertisementType) {
    if(type === AdvertisementType.FURNITURE) {
      this.advertisement = new FurnitureAdvertisement();
    } else if (type === AdvertisementType.JOB) {
        this.advertisement = new JobAdvertisement();
    } else if (type === AdvertisementType.REALTY) {
      this.advertisement = new RealtyAdvertisement();
    } else if (type === AdvertisementType.SERVICE) {
      this.advertisement = new ServiceAdvertisement();
    } else {
      throw new Error("Tipo de anúncio inválido.");
    }
  }

  get() {

  }
}
