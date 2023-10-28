package linketinder.zg.model.Skill

import java.sql.*

import linketinder.zg.db.ConnectionJDBC

import static linketinder.zg.util.ClearConsole.*
import static linketinder.zg.util.HandleExceptionDB.*
import static linketinder.zg.util.PrepareStatement.*
import static linketinder.zg.util.GetRowCount.*

import static linketinder.zg.view.Skills.ListSkills.*
import static linketinder.zg.view.Skills.UpdateSkill.*


class SkillDAO {
    public static final String SEARCH_ID_SKILL = "SELECT id FROM competencias WHERE nome = ?"
    public static final String INSERT_SKILL = "INSERT INTO competencias (nome) VALUES (?)"
    public static final String SEARCH_SKILL_BY_ID = "SELECT * FROM competencias WHERE id=?"
    public static final String UPDATE_SKILL = "UPDATE competencias SET nome=? WHERE id=?"
    public static final String DELETE_SKILL = "DELETE FROM competencias WHERE id=?"
    public static final String SEARCH_ALL_SKILLS = "SELECT * FROM competencias"

    static void list() {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement allSkills = prepareAllStatement(connection, SEARCH_ALL_SKILLS)
            ResultSet resultSet = allSkills.executeQuery()

            int skillCount = getRowCount(resultSet)

            if (skillCount > 0) {
                textListSkill(resultSet)
                ConnectionJDBC.disconnect(connection)

            } else {
                println("Não existem compêtencias cadastradas")
            }

        } catch (Exception e) {
            handleExceptionDB(e, "listar")
        }
    }

    static void create(Skill skill) {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement skillById = connection.prepareStatement(SEARCH_ID_SKILL)
            skillById.setString(1, skill.name)
            ResultSet resultSet = skillById.executeQuery()

            if (!resultSet.next()) {
                setSkillParameters(connection, skill)

                System.out.println("A competência " + skill.name + " foi inserida com sucesso.")
            } else {
                System.err.println("A competência " + skill.name + " já existe.")
            }

            skillById.close()
            ConnectionJDBC.disconnect(connection)

        } catch (Exception e) {
            handleExceptionDB(e, "criar")
        }
    }

    static void update(int id) {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement skillById = prepareByIdStatement(id, connection, SEARCH_SKILL_BY_ID)
            ResultSet resultSet = skillById.executeQuery()

            int skillCount = getRowCount(resultSet)

            if (skillCount > 0) {
                clearConsole()

                Skill skill = inputsUpdateSkill()
                setSkillUpdateParameters(connection, skill, id)

                clearConsole()
                System.out.println("Competência com ID " + id + " atualizada com sucesso.")
            } else {
                clearConsole()
                System.out.println("Não existe uma competência com o ID informado.")
            }
            ConnectionJDBC.disconnect(connection)
        } catch (SQLException e) {
            handleExceptionDB(e, "atualizar")
        }
    }

    static void delete(int id) {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement skillByID = prepareByIdStatement(id, connection, SEARCH_SKILL_BY_ID)
            ResultSet resultSet = skillByID.executeQuery()

            int skillCount = getRowCount(resultSet)

            if (skillCount > 0) {
                prepareDeleteStatement(connection, id)
                clearConsole()
                print("A competência com ID " + id + " deletado com sucesso.")

            } else {
                clearConsole()
                println("Não há competência com o id informado.")

            }
        } catch (SQLException e) {
            handleExceptionDB(e, "deletar")
        }
    }

    static void setSkillParameters(Connection connection, Skill skill) {
        PreparedStatement insertSkill = connection.prepareStatement(INSERT_SKILL)
        insertSkill.setString(1, skill.name)

        insertSkill.executeUpdate()
        insertSkill.close()
    }

    static void setSkillUpdateParameters(Connection connection, Skill skill, int id) {
        PreparedStatement atualizarCompetencia = connection.prepareStatement(UPDATE_SKILL)
        atualizarCompetencia.setString(1, skill.name)
        atualizarCompetencia.setInt(2, id)
        atualizarCompetencia.executeUpdate()
    }

    static void prepareDeleteStatement(Connection connection, int id) {
        PreparedStatement deletarCompetencia = connection.prepareStatement(DELETE_SKILL)
        deletarCompetencia.setInt(1, id)
        deletarCompetencia.executeUpdate()
    }
}