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
import { LoginPageComponent } from './login/login-page/login-page.component';
import { AuthGuardProvider } from './providers/auth-guard.provider';
import { SignupPageComponent } from './signup/signup-page/signup-page.component';
import { ConnectPageComponent } from './connect-page/connect-page.component';

@NgModule({
  declarations: [
    AppComponent,
    SignupFormComponent,
    LoginFormComponent,
    MainComponent,
    DashboardComponent,
    LoginPageComponent,
    SignupFormComponent,
    SignupPageComponent,
    ConnectPageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule, 
    AppRoutingModule
  ],
  providers: [
    AuthenticationService,
    AuthGuardProvider
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
