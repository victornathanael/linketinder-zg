package linketinder.zg.util

import linketinder.zg.model.Candidate.Candidate

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import static linketinder.zg.util.ConvertToListOfSkills.convertToListOfSkills

class CandidateParameters {
    static Candidate setListCandidateParameters(ResultSet resultSet) {
        Candidate candidate = new Candidate()
        candidate.setId(resultSet.getInt("id"))
        candidate.setName(resultSet.getString("nome").trim())
        candidate.setEmail(resultSet.getString("email").trim())
        candidate.setCpf(resultSet.getString("cpf").trim())
        candidate.setAge(resultSet.getInt("idade"))
        candidate.setState(resultSet.getString("estado").trim())
        candidate.setCep(resultSet.getString("cep").trim())
        candidate.setPersonalDescription(resultSet.getString("descricao").trim())

        String skillsString = resultSet.getString("competencias".trim())
        candidate.setSkills(convertToListOfSkills(skillsString))
        return candidate
    }

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
