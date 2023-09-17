import {Candidato} from "../../classes/Candidato";
import {validarNome, validarEmail, validarCPF, validarIdade, validarCEP} from "./validation";

const nomeCandidato: HTMLInputElement = document.getElementById('name') as HTMLInputElement
const emailCandidato: HTMLInputElement = document.getElementById('email') as HTMLInputElement
const cpfCandidato: HTMLInputElement = document.getElementById('cpf') as HTMLInputElement
const idadeCandidato: HTMLInputElement = document.getElementById('age') as HTMLInputElement
const estadoCandidato: HTMLInputElement = document.getElementById('state') as HTMLInputElement
const cepCandidato: HTMLInputElement = document.getElementById('cep') as HTMLInputElement
const descricaoCandidato: HTMLInputElement = document.getElementById('description') as HTMLInputElement
const competenciasCandidato: HTMLInputElement = document.getElementById('skills') as HTMLInputElement

const nomeError: HTMLElement = document.getElementById('name-error') as HTMLElement
const emailError: HTMLElement = document.getElementById('email-error') as HTMLElement
const cpfError: HTMLElement = document.getElementById('cpf-error') as HTMLElement
const idadeError: HTMLElement = document.getElementById('age-error') as HTMLElement
const cepError: HTMLElement = document.getElementById('cep-error') as HTMLElement

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
        if(validarNome(nomeCandidato.value) && validarEmail(emailCandidato.value) && validarCPF(cpfCandidato.value) && validarIdade(idadeCandidato.value) && validarCEP(cepCandidato.value)) {
            criarCandidato();
            limparInputCandidato();
            limparInputErro();
        } else {
            window.scrollTo({top: 0, behavior: 'smooth'});

            limparInputErro()

            if(!validarNome(nomeCandidato.value)) {
                nomeCandidato.style.border = '1px solid red'
                nomeError.style.display = 'block'
            }

            if (!validarEmail(emailCandidato.value)) {
                emailCandidato.style.border = '1px solid red'
                emailError.style.display = 'block'
            }

            if (!validarCPF(cpfCandidato.value)) {
                cpfCandidato.style.border = '1px solid red'
                cpfError.style.display = 'block'
            }

            if(!validarIdade(idadeCandidato.value)) {
                idadeCandidato.style.border = '1px solid red'
                idadeError.style.display = 'block'
            }

            if (!validarCEP(cepCandidato.value)) {
                cepCandidato.style.border = '1px solid red'
                cepError.style.display = 'block'
            }
        }
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

function limparInputErro(): void {
    nomeCandidato.style.border = '1px solid #EFEFEF'
    emailCandidato.style.border = '1px solid #EFEFEF'
    cpfCandidato.style.border = '1px solid #EFEFEF'
    idadeCandidato.style.border = '1px solid #EFEFEF'
    cepCandidato.style.border = '1px solid #EFEFEF'

    nomeError.style.display = 'none'
    emailError.style.display = 'none'
    cpfError.style.display = 'none'
    idadeError.style.display = 'none'
    cepError.style.display = 'none'
}