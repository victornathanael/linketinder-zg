import {Empresa} from '../../classes/empresa';

const nomeEmpresa: HTMLInputElement = document.getElementById('name') as HTMLInputElement
const emailEmpresa: HTMLInputElement = document.getElementById('email') as HTMLInputElement
const cnpjEmpresa: HTMLInputElement = document.getElementById('cnpj') as HTMLInputElement
const paisEmpresa: HTMLInputElement = document.getElementById('country') as HTMLInputElement
const estadoEmpresa: HTMLInputElement = document.getElementById('state') as HTMLInputElement
const cepEmpresa: HTMLInputElement = document.getElementById('cep') as HTMLInputElement
const descricaoEmpresa: HTMLInputElement = document.getElementById('description') as HTMLInputElement
const competenciasEmpresa: HTMLInputElement = document.getElementById('skills') as HTMLInputElement

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
        criarEmpresa()
        limparInputEmpresa()
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
