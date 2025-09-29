export class Cliente {
    idPessoa: number;
    nome: string;
    cpf: string;
    dataNascimento!: string;
    email: string;
    senha: string;
    status: boolean;

    constructor() {
        this.idPessoa = 0;
        this.nome = "";
        this.cpf = "";
        this.email = "";
        this.senha = "";
        this.status = true;
    }
}