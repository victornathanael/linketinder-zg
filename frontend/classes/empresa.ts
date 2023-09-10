export class Empresa {
    constructor(
        private nome: string,
        private email: string,
        private cnpj: string,
        private pais: string,
        private estado: string,
        private cep: string,
        private descricao: string,
        private competencias: string
    ) {
    }
}
