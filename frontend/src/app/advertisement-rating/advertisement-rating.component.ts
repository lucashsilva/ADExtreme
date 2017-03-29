import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { AdvertisementService } from '../services/advertisement.service';
import { Advertisement, FurnitureAdvertisement, JobAdvertisement, RealtyAdvertisement, ServiceAdvertisement } from '../models/advertisement';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-advertisement-rating',
  templateUrl: './advertisement-rating.component.html',
  styleUrls: ['./advertisement-rating.component.scss']
})
export class AdvertisementRatingComponent implements OnInit {

  advertisement: Advertisement;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: AdvertisementService
  ) {}

  ngOnInit() {
     this.route.params
    // (+) converts string 'id' to a number
    .switchMap((params: Params) => this.service.getAdById(+params['id']))
    .subscribe((advertisement: Advertisement) => this.advertisement = advertisement);
  }

}
