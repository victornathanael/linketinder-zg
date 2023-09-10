export class Candidato {
    constructor(
        private nome: string,
        private email: string,
        private cpf: string,
        private idade: string,
        private estado: string,
        private cep: string,
        private descricao: string,
        private competencias: string
    ) {
    }
}