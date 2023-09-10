import {Candidato} from "../../classes/candidato";

const nomeCandidato: HTMLInputElement = document.getElementById('name') as HTMLInputElement
const emailCandidato: HTMLInputElement = document.getElementById('email') as HTMLInputElement
const cpfCandidato: HTMLInputElement = document.getElementById('cpf') as HTMLInputElement
const idadeCandidato: HTMLInputElement = document.getElementById('age') as HTMLInputElement
const estadoCandidato: HTMLInputElement = document.getElementById('state') as HTMLInputElement
const cepCandidato: HTMLInputElement = document.getElementById('cep') as HTMLInputElement
const descricaoCandidato: HTMLInputElement = document.getElementById('description') as HTMLInputElement
const competenciasCandidato: HTMLInputElement = document.getElementById('skills') as HTMLInputElement

const criarCandidatoBtn: HTMLButtonElement = document.getElementById('create-candidate') as HTMLButtonElement

function validarEntradaCandidato(): boolean {
    const campos: HTMLInputElement[] = [
        nomeCandidato,
        emailCandidato,
        cpfCandidato,
        idadeCandidato,
        estadoCandidato,
        cepCandidato,
        descricaoCandidato,
        competenciasCandidato
    ];

    return campos.every((campo: HTMLInputElement): boolean => campo.value.trim() !== '');
}

window.addEventListener('change', (): void => {
    if (validarEntradaCandidato()) {
        criarCandidatoBtn.removeAttribute('disabled');
    } else {
        criarCandidatoBtn.setAttribute('disabled', '');
    }
});

if (criarCandidatoBtn) {
    criarCandidatoBtn.addEventListener('click', (): void => {
        criarCandidato();
        limparInputCandidato();
    });
}

function criarCandidato(): void {
    const candidato: Candidato = new Candidato(
        nomeCandidato.value,
        emailCandidato.value,
        cpfCandidato.value,
        idadeCandidato.value,
        estadoCandidato.value,
        cepCandidato.value,
        descricaoCandidato.value,
        competenciasCandidato.value
    );
    salvarCandidatoNoLocalStorage(candidato);

}

function salvarCandidatoNoLocalStorage(candidato: Candidato): void {
    const candidatosSalvos = JSON.parse(localStorage.getItem('candidatos') || '[]');
    candidatosSalvos.push(candidato);
    localStorage.setItem('candidatos', JSON.stringify(candidatosSalvos));
}

function limparInputCandidato(): void {
    nomeCandidato.value = '';
    emailCandidato.value = '';
    cpfCandidato.value = '';
    idadeCandidato.value = '';
    estadoCandidato.value = '';
    cepCandidato.value = '';
    descricaoCandidato.value = '';
    competenciasCandidato.value = '';

    criarCandidatoBtn.setAttribute('disabled', '')
}