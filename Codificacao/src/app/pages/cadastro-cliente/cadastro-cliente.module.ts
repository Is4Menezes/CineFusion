import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { CadastroClientePageRoutingModule } from './cadastro-cliente-routing.module';

import { CadastroClientePage } from './cadastro-cliente.page';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    CadastroClientePageRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [CadastroClientePage]
})
export class CadastroClientePageModule {}
