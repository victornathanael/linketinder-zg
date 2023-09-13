const listaDeVagas = document.querySelector('.job-list')
const vagas = JSON.parse(localStorage.getItem('vagas') || '[]');

interface Vaga {
    nome: string
    descricao: string
    linguagem: string
}

if (!vagas.length) {
    const nenhumaVaga: HTMLElement = document.querySelector('p') as HTMLElement
    nenhumaVaga.innerText = 'Não há nenhuma vaga cadastrada.'
} else if (listaDeVagas) {
    vagas.forEach((vagas: Vaga): void => {

        const li: HTMLElement = document.createElement('li');
        li.classList.add('job-item');

        const nameElement: HTMLDivElement = document.createElement('div');
        nameElement.classList.add('job-name');
        nameElement.textContent = vagas.nome;

        const titleElement: HTMLDivElement = document.createElement('div');
        titleElement.classList.add('job-description');
        titleElement.textContent = vagas.descricao;

        const skillsElement: HTMLDivElement = document.createElement('div');
        skillsElement.classList.add('job-skills');
        skillsElement.textContent = vagas.linguagem;

        li.appendChild(nameElement);
        li.appendChild(titleElement);
        li.appendChild(skillsElement);

        listaDeVagas.appendChild(li);
    })
}
