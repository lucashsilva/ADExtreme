import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { AuthenticationService } from '../services/authentication.service';
import { Deposit } from '../models/deposit';

@Component({
  selector: 'app-deposit-card',
  templateUrl: './deposit-card.component.html',
  styleUrls: ['./deposit-card.component.scss']
})
export class DepositCardComponent implements OnInit {
  deposit: Deposit;

  constructor(private userService: UserService, private authenticationService: AuthenticationService) { 
    this.deposit = new Deposit();
  }

  ngOnInit() {
  }

  submit() {
    this.userService.deposit(this.deposit).then(res => {
      if(res) {
        alert("Depósito efetuado com sucesso.");
        location.reload();
      }
    });
  }

}
