import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastController } from '@ionic/angular';
import { NavController } from '@ionic/angular';
import { ClienteService } from 'src/app/services/cliente.service';
import { Cliente } from 'src/app/model/cliente';

@Component({
  selector: 'app-cadastro-cliente',
  templateUrl: './cadastro-cliente.page.html',
  styleUrls: ['./cadastro-cliente.page.scss'],
})
export class CadastroClientePage implements OnInit {

  cliente: Cliente;
  formGroup: FormGroup;
  emailInvalido: boolean;
  cpfInvalido: boolean;

  constructor(
    private formBuilder: FormBuilder,
    private clienteService: ClienteService,
    private toastController: ToastController,
    private navController: NavController
  ) {
    this.emailInvalido = false;
    this.cpfInvalido = false;
    this.cliente = new Cliente();
    this.formGroup = this.formBuilder.group({
      'nome': [this.cliente.nome, Validators.compose([Validators.required])],
      'email': [this.cliente.email, Validators.compose([Validators.required, Validators.email])],
      'cpf': [this.cliente.cpf, Validators.compose([Validators.required])],
      'dataNascimento': [this.cliente.dataNascimento, Validators.compose([Validators.required])],
      'senha': [this.cliente.senha, Validators.compose([Validators.required])],
    });
  }

  ngOnInit() { }

  salvar() {
    if (this.formGroup.valid) {
      this.cliente.nome = this.formGroup.value.nome;
      this.cliente.email = this.formGroup.value.email;
      this.cliente.cpf = this.formGroup.value.cpf;
      this.cliente.dataNascimento = this.formGroup.value.dataNascimento;
      this.cliente.senha = this.formGroup.value.senha;

      this.clienteService.verificarCpf(this.cliente.cpf)
        .then((json) => {
          this.cpfInvalido = <boolean>(json);
          if (!this.cpfInvalido) {
            this.clienteService.verificarLogin(this.cliente.email)
              .then((json) => {
                this.emailInvalido = <boolean>(json);
                if (!this.emailInvalido) {
                  this.clienteService.salvar(this.cliente)
                    .then((json) => {
                      this.cliente = <Cliente>(json);
                      if (this.cliente) {
                        this.exibirMensagem('Cadastro realizado com sucesso!');
                        this.navController.navigateBack('/login-cliente');
                      }
                    })
                    .catch((erro => {
                      this.exibirMensagem('Erro ao salvar o registro! Erro: ' + erro['mensage']);
                    }));
                }
              })
              .catch((erro) => {
                this.exibirMensagem('Erro ao realizar o cadastro! Erro: ' + erro['message']);
              });
          }
        })
        .catch((erro => {
          this.exibirMensagem('Erro ao salvar o registro! Erro: ' + erro['mensage']);
        }));
    } else {
      this.exibirMensagem('Por favor, preencha todos os campos corretamente.');
    }
  }

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present();
  }

}
