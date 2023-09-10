const candidatosSalvos = JSON.parse(localStorage.getItem('candidatos') || '[]');

const candidates = candidatosSalvos.map(candidato => ({
    name: candidato.nome.toLowerCase(),
    skills: candidato.competencias.toLowerCase().split(', ').map(skill => skill.trim()),
}));

const skillCount = {};

candidates.forEach(candidate => {
    candidate.skills.forEach(skill => {
        skillCount[skill] = (skillCount[skill] || 0) + 1;
    });
});

const labels = Object.keys(skillCount);
const data = Object.values(skillCount);

const ctx = document.getElementById("skillsChart").getContext("2d");
new Chart(ctx, {
    type: "bar",
    data: {
        labels: labels,
        datasets: [{
            label: "Número de Candidatos por Competência",
            data: data,
            backgroundColor: "rgba(75, 192, 192, 0.2)",
            borderColor: "rgba(75, 192, 192, 1)",
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});
