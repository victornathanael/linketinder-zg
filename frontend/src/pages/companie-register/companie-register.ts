import {Empresa} from "../../classes/Empresa";
import {validarNome, validarEmail, validarCNPJ, validarCEP} from "./validation";

const nomeEmpresa: HTMLInputElement = document.getElementById('name') as HTMLInputElement
const emailEmpresa: HTMLInputElement = document.getElementById('email') as HTMLInputElement
const cnpjEmpresa: HTMLInputElement = document.getElementById('cnpj') as HTMLInputElement
const paisEmpresa: HTMLInputElement = document.getElementById('country') as HTMLInputElement
const estadoEmpresa: HTMLInputElement = document.getElementById('state') as HTMLInputElement
const cepEmpresa: HTMLInputElement = document.getElementById('cep') as HTMLInputElement
const descricaoEmpresa: HTMLInputElement = document.getElementById('description') as HTMLInputElement
const competenciasEmpresa: HTMLInputElement = document.getElementById('skills') as HTMLInputElement

const nomeError: HTMLElement = document.getElementById('name-error') as HTMLElement
const emailError: HTMLElement = document.getElementById('email-error') as HTMLElement
const cnpjError: HTMLElement = document.getElementById('cnpj-error') as HTMLElement
const cepError: HTMLElement = document.getElementById('cep-error') as HTMLElement

const criarEmpresaBtn: HTMLButtonElement = document.getElementById('create-companie') as HTMLButtonElement

function validarEntradaEmpresa(): boolean {
    const campos: HTMLInputElement[] = [
        nomeEmpresa,
        emailEmpresa,
        cnpjEmpresa,
        paisEmpresa,
        estadoEmpresa,
        cepEmpresa,
        descricaoEmpresa,
        competenciasEmpresa
    ];

    return campos.every((campo: HTMLInputElement): boolean => campo.value.trim() !== '');
}

window.addEventListener('change', (): void => {
    if (validarEntradaEmpresa()) {
        criarEmpresaBtn.removeAttribute('disabled');
    } else {
        criarEmpresaBtn.setAttribute('disabled', '');
    }
});

if (criarEmpresaBtn) {
    criarEmpresaBtn.addEventListener('click', (): void => {
        if(validarNome(nomeEmpresa.value) && validarEmail(emailEmpresa.value) && validarCNPJ(cnpjEmpresa.value) && validarCEP(cepEmpresa.value)) {
            criarEmpresa();
            limparInputEmpresa();
            limparInputErro();
        } else {
            window.scrollTo({top: 0, behavior: 'smooth'});

            limparInputErro()

            if(!validarNome(nomeEmpresa.value)) {
                nomeEmpresa.style.border = '1px solid red'
                nomeError.style.display = 'block'
            }

            if (!validarEmail(emailEmpresa.value)) {
                emailEmpresa.style.border = '1px solid red'
                emailError.style.display = 'block'
            }

            if (!validarCNPJ(cnpjEmpresa.value)) {
                cnpjEmpresa.style.border = '1px solid red'
                cnpjError.style.display = 'block'
            }

            if (!validarCEP(cepEmpresa.value)) {
                cepEmpresa.style.border = '1px solid red'
                cepError.style.display = 'block'
            }
        }
    });
}

function criarEmpresa(): void {
    const empresa: Empresa = new Empresa(
        nomeEmpresa.value,
        emailEmpresa.value,
        cnpjEmpresa.value,
        paisEmpresa.value,
        estadoEmpresa.value,
        cepEmpresa.value,
        descricaoEmpresa.value,
        competenciasEmpresa.value
    );
    salvarEmpresaNoLocalStorage(empresa);
}

function salvarEmpresaNoLocalStorage(empresa: Empresa): void {
    const empresasSalvas: Empresa[] = JSON.parse(localStorage.getItem('empresas') || '[]');
    empresasSalvas.push(empresa);
    localStorage.setItem('empresas', JSON.stringify(empresasSalvas));
}

function limparInputEmpresa(): void {
    nomeEmpresa.value = '';
    emailEmpresa.value = '';
    cnpjEmpresa.value = '';
    paisEmpresa.value = '';
    estadoEmpresa.value = '';
    cepEmpresa.value = '';
    descricaoEmpresa.value = '';
    competenciasEmpresa.value = '';

    criarEmpresaBtn.setAttribute('disabled', '')
}

function limparInputErro(): void {
    nomeEmpresa.style.border = '1px solid #EFEFEF'
    emailEmpresa.style.border = '1px solid #EFEFEF'
    cnpjEmpresa.style.border = '1px solid #EFEFEF'
    cepEmpresa.style.border = '1px solid #EFEFEF'

    nomeError.style.display = 'none'
    emailError.style.display = 'none'
    cnpjError.style.display = 'none'
    cepError.style.display = 'none'
}