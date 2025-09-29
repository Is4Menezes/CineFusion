import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CadastroCinemaPage } from './cadastro-cinema.page';

const routes: Routes = [
  {
    path: '',
    component: CadastroCinemaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CadastroCinemaPageRoutingModule {}
