import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SignupFormComponent } from './signup/signup-form/signup-form.component';
import { AdvertisementFormComponent } from './advertisement-form/advertisement-form.component';
import { DepositCardComponent } from './deposit-card/deposit-card.component';

const routes: Routes = [
    { path: '', component: MainComponent, children: [
      { path: '', pathMatch: 'full', redirectTo: 'dashboard'},
      { path: 'dashboard', component: DashboardComponent },
      { path: 'signup', component: SignupFormComponent },
      { path: 'advertisements/new', component: AdvertisementFormComponent },
      { path: 'deposit', component: DepositCardComponent }
    ] }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}


/*
Copyright 2016 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/