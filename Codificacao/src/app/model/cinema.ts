import { Cidade } from "./cidade";

export class Cinema {
    id: number;
    nome: string;
    cnpj: string;
    email: string;
    senha: string;
    status: boolean;
    cidade!: Cidade;

    constructor() {
        this.id = 0;
        this.nome = "";
        this.cnpj = "";
        this.email = "";
        this.senha = "";
        this.status = true;
    }
}