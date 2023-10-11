package linketinder.zg.view.Candidates

import linketinder.zg.model.Candidate.CandidateDAO

class UpdateCandidate {
    static void updateCandidate() {
        ListCandidates.listCandidates()
        Scanner input = new Scanner(System.in)
        print("Informe o id do candidato : ");
        int id = input.nextInt();
        CandidateDAO.update(id, input)
    }
}
