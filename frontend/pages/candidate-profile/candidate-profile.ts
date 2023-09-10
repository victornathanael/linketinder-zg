const listaDeVagas = document.querySelector('.job-list')
const vagas = JSON.parse(localStorage.getItem('empresas') || '[]');

interface Vaga {
    descricao: string
    competencias: string
}

if (!vagas.length) {
    const nenhumaVaga: HTMLElement = document.querySelector('p') as HTMLElement
    nenhumaVaga.innerText = 'Não há nenhuma vaga cadastrada.'
} else if (listaDeVagas) {
    vagas.forEach((vagas: Vaga, index: number): void => {

        const li: HTMLElement = document.createElement('li');
        li.classList.add('job-item');

        const nameElement: HTMLDivElement = document.createElement('div');
        nameElement.classList.add('job-name');
        nameElement.textContent = `Vaga ${index + 1}`;

        const titleElement: HTMLDivElement = document.createElement('div');
        titleElement.classList.add('job-description');
        titleElement.textContent = vagas.descricao;

        const skillsElement: HTMLDivElement = document.createElement('div');
        skillsElement.classList.add('job-skills');
        skillsElement.textContent = vagas.competencias;

        li.appendChild(nameElement);
        li.appendChild(titleElement);
        li.appendChild(skillsElement);

        listaDeVagas.appendChild(li);
    })
}
