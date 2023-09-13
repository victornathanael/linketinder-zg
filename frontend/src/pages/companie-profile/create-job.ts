import {Vaga} from '../../classes/Vaga'

const nomeVaga: HTMLInputElement = document.getElementById('name') as HTMLInputElement
const descricaoVaga: HTMLInputElement = document.getElementById('description') as HTMLInputElement
const linguagemVaga: HTMLInputElement = document.getElementById('language') as HTMLInputElement

const criarVagaBtn: HTMLButtonElement = document.getElementById('create-job') as HTMLButtonElement

function validarEntradaVaga(): boolean {
    const campos: HTMLInputElement[] = [
        nomeVaga,
        descricaoVaga,
        linguagemVaga,
    ];

    return campos.every((campo: HTMLInputElement): boolean => campo.value.trim() !== '');
}

window.addEventListener('change', (): void => {
    if (validarEntradaVaga()) {
        criarVagaBtn.removeAttribute('disabled');
    } else {
        criarVagaBtn.setAttribute('disabled', '');
    }
});

if (criarVagaBtn) {
    criarVagaBtn.addEventListener('click', (): void => {
        criarVaga()
        limparInputVaga()
    });
}

function criarVaga(): void {
    const vaga: Vaga = new Vaga(
        nomeVaga.value,
        descricaoVaga.value,
        linguagemVaga.value,
    );
    salvarVagaNoLocalStorage(vaga);
}

function salvarVagaNoLocalStorage(vaga: Vaga): void {
    const vagasSalvas = JSON.parse(localStorage.getItem('vagas') || '[]');
    vagasSalvas.push(vaga);
    localStorage.setItem('vagas', JSON.stringify(vagasSalvas));
}


function limparInputVaga(): void {
    nomeVaga.value = '';
    descricaoVaga.value = '';
    linguagemVaga.value = '';

    criarVagaBtn.setAttribute('disabled', '')
}