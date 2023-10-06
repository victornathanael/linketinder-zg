package view.Candidates

import model.Candidate.CandidateDAO

class UpdateCandidate {
    static void updateCandidate() {
        ListCandidates.listCandidates()
        Scanner input = new Scanner(System.in)
        print("Informe o id do candidato : ");
        int id = input.nextInt();
        CandidateDAO.update(id, input)
    }
}
