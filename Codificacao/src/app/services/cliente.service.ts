import { Injectable } from '@angular/core';
import { Cliente } from '../model/cliente';
import { firstValueFrom } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  url: string = 'http://localhost:8087/api/pessoa';

  constructor(private httpClient: HttpClient) { }

  async salvar(cliente: Cliente): Promise<Cliente> {
    if (cliente.idPessoa === 0) {
      return await firstValueFrom(this.httpClient.post<Cliente>(this.url, JSON.stringify(cliente), this.httpHeaders));
    } else {
      return await firstValueFrom(this.httpClient.put<Cliente>(this.url, JSON.stringify(cliente), this.httpHeaders));
    }
  }

  async listar(): Promise<Cliente[]> {
    return await firstValueFrom(this.httpClient.get<Cliente[]>(this.url));
  }

  async buscarPorId(idPessoa: number): Promise<Cliente> {
    let urlAuxiliar = this.url + "/" + idPessoa;
    return await firstValueFrom(this.httpClient.get<Cliente>(urlAuxiliar));
  }

  async excluir(idPessoa: number): Promise<Cliente> {
    let urlAuxiliar = this.url + "/" + idPessoa;
    return await firstValueFrom(this.httpClient.delete<Cliente>(urlAuxiliar));
  }

  async autenticar(email: string, senha: string): Promise<Cliente> {
    let urlAuxiliar = this.url + "/" + email + "/" + senha + '/authenticate';
    return await firstValueFrom(this.httpClient.get<Cliente>(urlAuxiliar));
  }

  async verificarLogin(email: string): Promise<boolean> {
    let urlAuxiliar = this.url + "/" + email + '/exists';
    return await firstValueFrom(this.httpClient.get<boolean>(urlAuxiliar));
  }

  async verificarCpf(cpf: string): Promise<boolean> {
    let urlAuxiliar = this.url + "/" + cpf + '/cpf/exists';
    return await firstValueFrom(this.httpClient.get<boolean>(urlAuxiliar));
  }

  registrarAutenticacao(cliente: Cliente) {
    localStorage.setItem('usuarioAutenticado', JSON.stringify(cliente));
  }

  recuperarAutenticacao(): Cliente {
    let cliente = new Cliente();
    cliente = JSON.parse(localStorage.getItem('usuarioAutenticado') || "{}");
    return cliente;
  }

  encerrarAutenticacao() {
    localStorage.removeItem('usuarioAutenticado');
  }
}