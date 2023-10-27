package linketinder.zg.view.Candidates

import linketinder.zg.model.Candidate.Candidate
import linketinder.zg.model.Candidate.CandidateDAO
import linketinder.zg.util.InputUtils

class UpdateCandidate {
    static void updateCandidate() {
        ListCandidates.listCandidates()
        Scanner input = new Scanner(System.in)
        print("Informe o id do candidato : ")
        int id = input.nextInt()
        CandidateDAO.update(id, input)
    }

    static Candidate inputsUpdateCandidate() {
        String name = InputUtils.getStringInput("Digite o nome do novo candidato: ");
        String email = InputUtils.getStringInput("Digite o email do novo candidato: ");
        String cpf = InputUtils.getStringInput("Digite o cpf do novo candidato: ");
        int age = InputUtils.getIntInput("Digite a idade do novo candidato: ");
        InputUtils.getStringInput("");
        String state = InputUtils.getStringInput("Digite o estado do novo candidato: ");
        String cep = InputUtils.getStringInput("Digite o CEP do novo candidato: ");
        String personalDescription = InputUtils.getStringInput("Digite a descrição do novo candidato: ");

        Candidate updatedCandidateInputs = new Candidate(name, email, cpf, age, state, cep, personalDescription)

        return updatedCandidateInputs
    }
}
