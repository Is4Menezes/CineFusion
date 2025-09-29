import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginCinemaPage } from './login-cinema.page';

const routes: Routes = [
  {
    path: '',
    component: LoginCinemaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LoginCinemaPageRoutingModule {}
