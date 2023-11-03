package linketinder.zg.model.Candidate

import linketinder.zg.db.factory.ConnectionProviderFactory
import linketinder.zg.db.factory.DatabaseType
import linketinder.zg.db.factory.IConnectionProvider
import org.postgresql.core.ConnectionFactory

import java.sql.*
import linketinder.zg.db.PostgreSQLConnection

import static linketinder.zg.view.Candidates.ListCandidates.*

import static linketinder.zg.util.ClearConsole.*
import static linketinder.zg.util.GetSkillId.getSkillId
import static linketinder.zg.util.GetRowCount.*
import static linketinder.zg.util.HandleExceptionDB.*
import static linketinder.zg.util.PrepareStatement.*
import static linketinder.zg.util.CandidateParameters.*
import static linketinder.zg.util.LinkSkillWith.*
import static linketinder.zg.util.UpdateExistingInDataBase.*

class CandidateDAO {
    private static final String INSERT_CANDIDATE = "INSERT INTO candidatos (nome, email, cpf, idade, estado, cep, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)"
    private static final String INSERT_SKILLS = "INSERT INTO competencias (nome) VALUES (?)"
    private static final String VERIFY_SKILL = "SELECT id FROM competencias WHERE nome = ?"
    private static final String INSERT_LINK_CANDIDATE_SKILL = "INSERT INTO competencias_candidatos(candidato_id, competencia_id) VALUES (?, ?)"
    private static final String UPDATE_CANDIDATE = "UPDATE candidatos SET nome=?, email=?, cpf=?, idade=?, estado=?, cep=?, descricao=? WHERE id=?"
    private static final String SEARCH_CANDIDATE_BY_ID = "SELECT * FROM candidatos WHERE id=?"
    private static final String DELETE_CANDIDATE = "DELETE FROM candidatos WHERE id=?"
    private static final String SEARCH_ALL_CANDIDATES = "SELECT c.id, c.nome, c.email, c.cpf, STRING_AGG(co.nome, ', ') AS competencias FROM candidatos c LEFT JOIN competencias_candidatos cc ON c.id = cc.candidato_id LEFT JOIN competencias co ON cc.competencia_id = co.id GROUP BY c.id"

    private static final IConnectionProvider connectionProvider = ConnectionProviderFactory.createConnectionProvider(DatabaseType.POSTGRE)

    static void list() {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement allCandidates = prepareAllStatement(connection, SEARCH_ALL_CANDIDATES)
            ResultSet resultSet = allCandidates.executeQuery()

            int candidateCount = getRowCount(resultSet)

            if (candidateCount > 0) {
                textListCandidate(resultSet)
                connectionProvider.disconnect()

            } else {
                println("Não existem candidatos cadastrados")
            }

        } catch (Exception e) {
            handleExceptionDB(e, "listar")
        }
    }

    static void create(Candidate candidate) {
        try (Connection connection = connectionProvider.connect()) {
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

                        linkSkillWith(linkCandidateSkill, candidateId, skillId)
                    }
                }
                generatedKeys.close()
            }

            saveCandidate.close()
            saveSkill.close()
            verifySkill.close()
            linkCandidateSkill.close()

            connectionProvider.disconnect()

            System.out.println("O candidato " + candidate.name + " foi inserido com sucesso.")
        } catch (Exception e) {
            handleExceptionDB(e, "criar")
        }
    }

    static void update(int id) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement candidateById = prepareByIdStatement(id, connection, SEARCH_CANDIDATE_BY_ID);
            ResultSet resultSet = candidateById.executeQuery();

            int candidateCount = getRowCount(resultSet)

            if (candidateCount > 0) {
                clearConsole()
                updateExistingCandidateInDataBase(id, connection, UPDATE_CANDIDATE)
            } else {
                clearConsole()
                println("Não existe um candidato com o id informado.")
            }
        } catch (SQLException e) {
            handleExceptionDB(e, "atualizar")
        }
    }

    static void delete(int id) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement candidateById = prepareByIdStatement(id, connection, SEARCH_CANDIDATE_BY_ID);
            ResultSet resultSet = candidateById.executeQuery();

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
}
