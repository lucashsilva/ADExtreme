import { Component, OnInit } from '@angular/core';
import { Advertisement } from '../models/advertisement';

@Component({
  selector: 'app-advertisement-card',
  templateUrl: './advertisement-card.component.html',
  styleUrls: ['./advertisement-card.component.scss']
})
export class AdvertisementCardComponent implements OnInit {
  advertisements: Array<Advertisement>;

  constructor() {
    this.advertisements = new Array<Advertisement>();

   let ad1 = new Advertisement();
   // mocked data
    ad1.title = "Título teste";
    ad1.publicationDate = new Date(Date.now());
    ad1.expirationDate = new Date(Date.now() + 200000);
    ad1.value = 299.99;

    this.advertisements.push(ad1);
  }

  ngOnInit() {
  }

}
