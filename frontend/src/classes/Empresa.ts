export class Empresa {
    constructor(
        public nome: string,
        public email: string,
        public cnpj: string,
        public pais: string,
        public estado: string,
        public cep: string,
        public descricao: string,
        public competencias: string
    ) {
    }
}