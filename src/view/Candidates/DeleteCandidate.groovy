package view.Candidates

import model.Candidate.CandidateDAO

class DeleteCandidate {
    static void deleteCandidate() {
        ListCandidates.listCandidates()
        Scanner input = new Scanner(System.in)
        print("Informe o id do candidato : ");
        int id = input.nextInt();
        CandidateDAO.delete(id)
    }
}
