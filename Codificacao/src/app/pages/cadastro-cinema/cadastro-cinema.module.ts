import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { CadastroCinemaPageRoutingModule } from './cadastro-cinema-routing.module';

import { CadastroCinemaPage } from './cadastro-cinema.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    CadastroCinemaPageRoutingModule
  ],
  declarations: [CadastroCinemaPage]
})
export class CadastroCinemaPageModule {}
