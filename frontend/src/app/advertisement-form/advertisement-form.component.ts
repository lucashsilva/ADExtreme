import { Component, OnInit } from '@angular/core';
import { Advertisement, FurnitureAdvertisement, JobAdvertisement, RealtyAdvertisement, ServiceAdvertisement } from '../models/advertisement';
import { AuthenticationService } from '../services/authentication.service';
import { AdvertisementService } from '../services/advertisement.service';
import { UserRole } from '../models/user';

@Component({
  selector: 'app-advertisement-form',
  templateUrl: './advertisement-form.component.html',
  styleUrls: ['./advertisement-form.component.scss']
})
export class AdvertisementFormComponent implements OnInit {
  private ad: Advertisement;
  private selectedType: boolean;

  constructor(private authenticationService: AuthenticationService, private advertisementService: AdvertisementService) {   }

  ngOnInit() {
  }

  initType(type: string) {
    if(type === "FURNITURE") {
      this.ad = new FurnitureAdvertisement();
    } else if (type === "JOB") {
        this.ad = new JobAdvertisement();
    } else if (type === "REALTY") {
      this.ad = new RealtyAdvertisement();
    } else if (type === "SERVICE") {
      this.ad = new ServiceAdvertisement();
    } else {
      throw new Error("Tipo de anúncio inválido.");
    }

    this.selectedType = true;
  }

  isLegalPerson() {
    return this.authenticationService.authenticatedUser.userInfo.role.toString() == "LEGAL_PERSON";
  }

  submit() {
    this.advertisementService.createAds(this.ad).then(success => {
      if(success) {
        //handle
      }
    });
  }
}

