package model

class CandidateDAO {
    static List<Candidate> candidates = [
            new Candidate(
                    name: "João",
                    email: "joao@gmail.com",
                    cpf: "123.456.789-00",
                    age: 25,
                    state: "São Paulo",
                    cep: "01234-567",
                    personalDescription: "Desenvolvedor apaixonado por tecnologia.",
                    skills: ["JavaScript", "Java", "CSS", "Angular", "Python", "C#", "PHP"]
            ),
            new Candidate(
                    name: "Maria",
                    email: "maria@gmail.com",
                    cpf: "987.654.321-00",
                    age: 28,
                    state: "Rio de Janeiro",
                    cep: "04567-890",
                    personalDescription: "Designer com 5 anos de experiência.",
                    skills: ["HTML", "CSS", "React", "Angular", "Vue.js", "Bootstrap", "jQuery"]
            ),
            new Candidate(
                    name: "Carlos",
                    email: "carlos@gmail.com",
                    cpf: "555.555.555-00",
                    age: 29,
                    state: "Minas Gerais",
                    cep: "67890-123",
                    personalDescription: "Engenheiro de software.",
                    skills: ["HTML", "React", "Node.js", "Swift", "Ruby", "Elasticsearch", "Django"]
            ),
            new Candidate(
                    name: "Ana",
                    email: "ana@gmail.com",
                    cpf: "111.222.333-00",
                    age: 18,
                    state: "Bahia",
                    cep: "45678-901",
                    personalDescription: "Estudante de design.",
                    skills: ["Git", "Docker", "Kubernetes", "Jenkins", "Travis CI", "CircleCI", "Ansible"]
            ),
            new Candidate(
                    name: "Pedro",
                    email: "pedro@gmail.com",
                    cpf: "444.444.444-00",
                    age: 48,
                    state: "Santa Catarina",
                    cep: "23456-789",
                    personalDescription: "Analista de negócios em busca de inovação.",
                    skills: ["MongoDB", "Redis", "Spring Boot", "HTML", "CSS", "Cassandra", "Travis CI"]
            )
    ]

    static void saveCandidate(String name, String email, String cpf, int age, String state, String cep, String personalDescription, String skills) {
        candidates.add(new Candidate(
                name: name,
                email: email,
                cpf: cpf,
                age: age,
                state: state,
                cep: cep,
                personalDescription: personalDescription,
                skills: [skills]
        ))
    }

}
