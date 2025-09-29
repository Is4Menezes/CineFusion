import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/model/cliente';
import { ClienteService } from 'src/app/services/cliente.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastController } from '@ionic/angular';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-login-cliente',
  templateUrl: './login-cliente.page.html',
  styleUrls: ['./login-cliente.page.scss'],
})
export class LoginClientePage implements OnInit {

  cliente: Cliente;
  formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder, private clienteService: ClienteService, private toastController: ToastController, private navController: NavController) {
    this.cliente = new Cliente();
    this.formGroup = this.formBuilder.group({
      'email': [this.cliente.email, Validators.compose([Validators.required])],
      'senha': [this.cliente.senha, Validators.compose([Validators.required])],
    })
  }

  ngOnInit() {
    this.clienteService.encerrarAutenticacao();
  }

  autenticar() {
    let email = this.formGroup.value.email;
    let senha = this.formGroup.value.senha;

    this.clienteService.autenticar(email, senha)
      .then((json) => {
        this.cliente = <Cliente>(json);

        if (this.cliente.idPessoa > 0) {
          this.clienteService.registrarAutenticacao(this.cliente);
          this.navController.navigateBack('/filme')
        } else {
          this.exibirMensagem('E-mail e/ou senha inválidos!!!')

        }
      })
      .catch((erro => {
        this.exibirMensagem('Erro ao salvar o registro! Erro: ' + erro['mensage']);
      }));
  }

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present()
  }

}
