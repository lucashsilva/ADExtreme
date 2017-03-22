import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { SignupFormComponent } from './signup/signup-form/signup-form.component';
import { LoginFormComponent } from './login/login-form/login-form.component';
import { AuthenticationService } from './services/authentication.service';
import { AppRoutingModule } from './app-routing.module';
import { MainComponent } from './main/main.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ConnectCardComponent } from './connect-card/connect-card.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AdvertisementComponent } from './advertisement/advertisement.component';
import { AdvertisementCardComponent } from './advertisement-card/advertisement-card.component'

@NgModule({
  declarations: [
    AppComponent,
    SignupFormComponent,
    LoginFormComponent,
    MainComponent,
    DashboardComponent,
    SignupFormComponent,
    SidebarComponent,
    ConnectCardComponent,
    NavbarComponent,
    AdvertisementComponent,
    AdvertisementCardComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule, 
    AppRoutingModule
  ],
  providers: [
    AuthenticationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
