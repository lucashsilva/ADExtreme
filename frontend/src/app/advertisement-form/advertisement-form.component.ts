import { Component, OnInit } from '@angular/core';
import { Advertisement, FurnitureAdvertisement, JobAdvertisement, RealtyAdvertisement, ServiceAdvertisement } from '../models/advertisement';
import { AuthenticationService } from '../services/authentication.service';
import { AdvertisementService } from '../services/advertisement.service';
import { UserRole } from '../models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-advertisement-form',
  templateUrl: './advertisement-form.component.html',
  styleUrls: ['./advertisement-form.component.scss']
})
export class AdvertisementFormComponent implements OnInit {
  private ad: Advertisement;
  private selectedType: boolean;

  constructor(private authenticationService: AuthenticationService, private advertisementService: AdvertisementService, private router: Router) {   }

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

  isAuthenticated() {
    if (this.authenticationService.authenticatedUser) {
      return true;
    } else return false;
  }

  submit() {
    this.advertisementService.createAds(this.ad).then(success => {
      if(success) {
        this.router.navigate(['dashboard']);
      }
    });
  }
}

