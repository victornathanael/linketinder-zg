package linketinder.zg.view.Candidates

import linketinder.zg.model.Candidate.Candidate
import linketinder.zg.model.Candidate.CandidateDAO
import linketinder.zg.model.Skill.Skill
import linketinder.zg.util.InputUtils

class NewCandidate {
    static void newCandidate() {
        CandidateDAO.create(inputsNewCandidate())
    }

    static Candidate inputsNewCandidate() {
        String name = InputUtils.getStringInput("Digite o nome do novo candidato: ");
        String email = InputUtils.getStringInput("Digite o email do novo candidato: ");
        String cpf = InputUtils.getStringInput("Digite o cpf do novo candidato: ");
        int age = InputUtils.getIntInput("Digite a idade do novo candidato: ");
        InputUtils.getStringInput("");
        String state = InputUtils.getStringInput("Digite o estado do novo candidato: ");
        String cep = InputUtils.getStringInput("Digite o CEP do novo candidato: ");
        String personalDescription = InputUtils.getStringInput("Digite a descrição do novo candidato: ");
        List<Skill> skills = InputUtils.getSkillsInput("Digite as competências do novo candidato (ex: java, angular, groovy): ")

        Candidate candidate = new Candidate(name, email, cpf, age, state, cep, personalDescription, skills)
        return candidate
    }
}
