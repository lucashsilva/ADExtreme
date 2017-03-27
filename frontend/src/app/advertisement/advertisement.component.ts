import { Component, OnInit, Input } from '@angular/core';
import { Advertisement, FurnitureAdvertisement, JobAdvertisement, RealtyAdvertisement, ServiceAdvertisement } from '../models/advertisement';

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

  get() {

  }
}
