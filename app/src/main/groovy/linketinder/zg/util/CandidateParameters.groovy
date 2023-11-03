package linketinder.zg.util

import linketinder.zg.model.Candidate.Candidate

import java.sql.PreparedStatement
import java.sql.SQLException

import static linketinder.zg.view.Candidates.UpdateCandidate.inputsUpdateCandidate

class CandidateParameters {
    static void setCandidateParameters(PreparedStatement saveCandidate, Candidate candidate) throws SQLException {
        saveCandidate.setString(1, candidate.name)
        saveCandidate.setString(2, candidate.email)
        saveCandidate.setString(3, candidate.cpf)
        saveCandidate.setInt(4, candidate.age)
        saveCandidate.setString(5, candidate.state)
        saveCandidate.setString(6, candidate.cep)
        saveCandidate.setString(7, candidate.personalDescription)

        saveCandidate.executeUpdate()
    }

    static void setUpdateCandidateParameters(PreparedStatement updateCandidate, int id) throws SQLException {
        Candidate updatedCandidateInputs = inputsUpdateCandidate()

        updateCandidate.setString(1, updatedCandidateInputs.name)
        updateCandidate.setString(2, updatedCandidateInputs.email)
        updateCandidate.setString(3, updatedCandidateInputs.cpf)
        updateCandidate.setInt(4, updatedCandidateInputs.age)
        updateCandidate.setString(5, updatedCandidateInputs.state)
        updateCandidate.setString(6, updatedCandidateInputs.cep)
        updateCandidate.setString(7, updatedCandidateInputs.personalDescription)
        updateCandidate.setInt(8, id)
    }

}
