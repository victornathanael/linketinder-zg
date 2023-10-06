package view.Candidates

import model.Candidate
import model.CandidateDAO

class NewCandidate {
    static void newCandidate() {
        Scanner input = new Scanner(System.in)

        print " Digite o nome do novo candidato: "
        String name = input.nextLine()

        print " Digite o email do novo candidato: "
        String email = input.nextLine()

        print " Digite o cpf do novo candidato: "
        String cpf = input.nextLine()

        print " Digite a idade do novo candidato: "
        int age = input.nextInt()
        input.nextLine()

        print " Digite o estado do novo candidato: "
        String state = input.nextLine()

        print " Digite o CEP do novo candidato: "
        String cep = input.nextLine()

        print " Digite a descrição do novo candidato: "
        String personalDescription = input.nextLine()

        print " Digite as competências do novo candidato (ex: java, angular, groovy): "
        String skills = input.nextLine()

        Candidate candidate = new Candidate(name, email, cpf, age, state, cep, personalDescription, skills)
        CandidateDAO.create(candidate)
    }
}
