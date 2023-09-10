const listaDeCandidatos = document.querySelector('.candidate-list')
const candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');

interface Candidato {
    competencias: string
    descricao: string
}

if (!candidatos.length) {
    const nenhumaVaga: HTMLElement = document.querySelector('p') as HTMLElement
    nenhumaVaga.innerText = 'Não há nenhum candidato cadastrado.'
} else if (listaDeCandidatos) {
    candidatos.forEach((candidatos: Candidato, index: number): void => {

        const li: HTMLLIElement = document.createElement('li');
        li.classList.add('candidate-item');

        const nameElement: HTMLDivElement = document.createElement('div');
        nameElement.classList.add('candidate-name');
        nameElement.textContent = `Candidato ${index + 1}`;

        const skillsElement: HTMLDivElement = document.createElement('div');
        skillsElement.classList.add('candidate-skills');
        skillsElement.textContent = candidatos.competencias;

        const titleElement: HTMLDivElement = document.createElement('div');
        titleElement.classList.add('candidate-description');
        titleElement.textContent = candidatos.descricao;

        li.appendChild(nameElement);
        li.appendChild(titleElement);
        li.appendChild(skillsElement);

        listaDeCandidatos.appendChild(li);
    })
}
