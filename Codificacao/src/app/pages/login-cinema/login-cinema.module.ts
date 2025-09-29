import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { LoginCinemaPageRoutingModule } from './login-cinema-routing.module';

import { LoginCinemaPage } from './login-cinema.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    LoginCinemaPageRoutingModule
  ],
  declarations: [LoginCinemaPage]
})
export class LoginCinemaPageModule {}
