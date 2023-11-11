package linketinder.zg.util

import linketinder.zg.model.Candidate.Candidate

import java.sql.PreparedStatement
import java.sql.SQLException

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

    static void setUpdateCandidateParameters(PreparedStatement updateCandidate, int id, Candidate candidate) throws SQLException {
        updateCandidate.setString(1, candidate.name)
        updateCandidate.setString(2, candidate.email)
        updateCandidate.setString(3, candidate.cpf)
        updateCandidate.setInt(4, candidate.age)
        updateCandidate.setString(5, candidate.state)
        updateCandidate.setString(6, candidate.cep)
        updateCandidate.setString(7, candidate.personalDescription)
        updateCandidate.setInt(8, id)

        updateCandidate.executeUpdate()
    }

}
