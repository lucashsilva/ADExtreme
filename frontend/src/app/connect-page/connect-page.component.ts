import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-connect-page',
  templateUrl: './connect-page.component.html',
  styleUrls: ['./connect-page.component.scss']
})
export class ConnectPageComponent implements OnInit {
  signup: boolean;

  constructor() { }

  ngOnInit() {
  }

  switchForm() {
    this.signup = !this.signup;
  }

}
