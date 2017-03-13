import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthGuardProvider } from './providers/auth-guard.provider';
import { ConnectPageComponent } from './connect-page/connect-page.component';

const routes: Routes = [
    { path: '', component: MainComponent, canActivate: [AuthGuardProvider], children: [
      { path: '', pathMatch: 'full', redirectTo: 'dashboard'},
      { path: 'dashboard', component: DashboardComponent }
    ] },
  { path: 'connect', component: ConnectPageComponent }
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