package linketinder.zg.model.Candidate

import java.sql.*
import linketinder.zg.db.ConnectionJDBC

import static linketinder.zg.view.Candidates.UpdateCandidate.*
import static linketinder.zg.view.Candidates.ListCandidates.*

import static linketinder.zg.util.ClearConsole.*
import static linketinder.zg.util.GetSkillId.getSkillId
import static linketinder.zg.util.GetRowCount.*
import static linketinder.zg.util.HandleExceptionDB.*
import static linketinder.zg.util.PrepareStatement.*

class CandidateDAO {
    private static final String INSERT_CANDIDATE = "INSERT INTO candidatos (nome, email, cpf, idade, estado, cep, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)"
    private static final String INSERT_SKILLS = "INSERT INTO competencias (nome) VALUES (?)"
    private static final String VERIFY_SKILL = "SELECT id FROM competencias WHERE nome = ?"
    private static final String INSERT_LINK_CANDIDATE_SKILL = "INSERT INTO competencias_candidatos(candidato_id, competencia_id) VALUES (?, ?)"
    private static final String UPDATE_CANDIDATE = "UPDATE candidatos SET nome=?, email=?, cpf=?, idade=?, estado=?, cep=?, descricao=? WHERE id=?"
    private static final String SEARCH_CANDIDATE_BY_ID = "SELECT * FROM candidatos WHERE id=?"
    private static final String DELETE_CANDIDATE = "DELETE FROM candidatos WHERE id=?"
    private static final String SEARCH_ALL_CANDIDATES = "SELECT c.id, c.nome, c.email, c.cpf, STRING_AGG(co.nome, ', ') AS competencias FROM candidatos c LEFT JOIN competencias_candidatos cc ON c.id = cc.candidato_id LEFT JOIN competencias co ON cc.competencia_id = co.id GROUP BY c.id"

    static void list() {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement allCandidates = prepareAllStatement(connection, SEARCH_ALL_CANDIDATES)
            ResultSet resultSet = allCandidates.executeQuery()
            allCandidates.close()

            int candidateCount = getRowCount(resultSet)

            if (candidateCount > 0) {
                textListCandidate(resultSet)
                ConnectionJDBC.disconnect(connection)

            } else {
                println("Não existem candidatos cadastrados")
            }

        } catch (Exception e) {
            handleExceptionDB(e, "listar")
        }
    }

    static void create(Candidate candidate) {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement saveCandidate = connection.prepareStatement(INSERT_CANDIDATE, Statement.RETURN_GENERATED_KEYS)
            PreparedStatement saveSkill = connection.prepareStatement(INSERT_SKILLS, Statement.RETURN_GENERATED_KEYS)
            PreparedStatement verifySkill = connection.prepareStatement(VERIFY_SKILL)
            PreparedStatement linkCandidateSkill = connection.prepareStatement(INSERT_LINK_CANDIDATE_SKILL)

            setCandidateParameters(saveCandidate, candidate)

            ResultSet generatedKeys = saveCandidate.getGeneratedKeys()
            if (generatedKeys.next()) {
                int candidateId = generatedKeys.getInt(1)

                if (!candidate.skills.isEmpty()) {
                    for (String skill : candidate.skills) {
                        int skillId = getSkillId(verifySkill, saveSkill, skill);

                        linkSkillToCandidate(linkCandidateSkill, candidateId, skillId)
                    }
                }
                generatedKeys.close()
            }

            saveCandidate.close()
            saveSkill.close()
            verifySkill.close()
            linkCandidateSkill.close()

            ConnectionJDBC.disconnect(connection)

            System.out.println("O candidato " + candidate.name + " foi inserido com sucesso.")
        } catch (Exception e) {
            handleExceptionDB(e, "criar")
        }
    }

    static void update(int id) {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement candidateById = prepareByIdStatement(id, connection, SEARCH_CANDIDATE_BY_ID);
            ResultSet resultSet = candidateById.executeQuery();
            candidateById.close()

            int candidateCount = getRowCount(resultSet)

            if (candidateCount > 0) {
                clearConsole()
                updateExistingCandidate(id, connection)
            } else {
                clearConsole()
                println("Não existe um candidato com o id informado.")
            }
        } catch (SQLException e) {
            handleExceptionDB(e, "atualizar")
        }
    }

    static void delete(int id) {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement candidateById = prepareByIdStatement(id, connection, SEARCH_CANDIDATE_BY_ID);
            ResultSet resultSet = candidateById.executeQuery();
            candidateById.close()

            int candidateCount = getRowCount(resultSet)

            if (candidateCount > 0) {
                prepareDeleteStatement(id, connection, DELETE_CANDIDATE)
                clearConsole()
                print("Candidato com ID " + id + " deletado com sucesso.")

            } else {
                clearConsole()
                println("Não existe um candidato com o id informado.")
            }
        } catch (SQLException e) {
            handleExceptionDB(e, "deletar")
        }
    }

    private static void setCandidateParameters(PreparedStatement saveCandidate, Candidate candidate) throws SQLException {
        saveCandidate.setString(1, candidate.name)
        saveCandidate.setString(2, candidate.email)
        saveCandidate.setString(3, candidate.cpf)
        saveCandidate.setInt(4, candidate.age)
        saveCandidate.setString(5, candidate.state)
        saveCandidate.setString(6, candidate.cep)
        saveCandidate.setString(7, candidate.personalDescription)

        saveCandidate.executeUpdate()
    }

    private static void updateExistingCandidate(int id, Connection connection) throws SQLException {
        PreparedStatement updateCandidate = connection.prepareStatement(UPDATE_CANDIDATE)
        setUpdateCandidateParameters(updateCandidate, id)
        updateCandidate.executeUpdate()

        clearConsole()
        print("Candidato com ID " + id + " atualizado com sucesso.")
    }

    private static void setUpdateCandidateParameters(PreparedStatement updateCandidate, int id) throws SQLException {
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

    private static void linkSkillToCandidate(PreparedStatement linkCandidateSkill, int candidateId, int skillId) throws SQLException {
        linkCandidateSkill.setInt(1, candidateId)
        linkCandidateSkill.setInt(2, skillId)
        linkCandidateSkill.executeUpdate()
    }

}
