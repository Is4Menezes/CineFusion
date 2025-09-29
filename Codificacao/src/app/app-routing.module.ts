import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'inicio',
    pathMatch: 'full'
  },
  {
    path: 'cadastro-cliente',
    loadChildren: () => import('./pages/cadastro-cliente/cadastro-cliente.module').then( m => m.CadastroClientePageModule)
  },
  {
    path: 'cadastro-cinema',
    loadChildren: () => import('./pages/cadastro-cinema/cadastro-cinema.module').then( m => m.CadastroCinemaPageModule)
  },
  {
    path: 'login-cliente',
    loadChildren: () => import('./pages/login-cliente/login-cliente.module').then( m => m.LoginClientePageModule)
  },
  {
    path: 'login-cinema',
    loadChildren: () => import('./pages/login-cinema/login-cinema.module').then( m => m.LoginCinemaPageModule)
  },
  {
    path: 'filme',
    loadChildren: () => import('./pages/filme/filme.module').then( m => m.FilmePageModule)
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
