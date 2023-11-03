package linketinder.zg.model.Skill

import linketinder.zg.db.factory.ConnectionProviderFactory
import linketinder.zg.db.factory.DatabaseType
import linketinder.zg.db.factory.IConnectionProvider

import java.sql.*


import static linketinder.zg.util.ClearConsole.*
import static linketinder.zg.util.HandleExceptionDB.*
import static linketinder.zg.util.PrepareStatement.*
import static linketinder.zg.util.GetRowCount.*
import static linketinder.zg.util.SkillParameters.*

import static linketinder.zg.view.Skills.ListSkills.*
import static linketinder.zg.view.Skills.UpdateSkill.*


class SkillDAO {
    public static final String SEARCH_ID_SKILL = "SELECT id FROM competencias WHERE nome = ?"
    public static final String INSERT_SKILL = "INSERT INTO competencias (nome) VALUES (?)"
    public static final String SEARCH_SKILL_BY_ID = "SELECT * FROM competencias WHERE id=?"
    public static final String UPDATE_SKILL = "UPDATE competencias SET nome=? WHERE id=?"
    public static final String DELETE_SKILL = "DELETE FROM competencias WHERE id=?"
    public static final String SEARCH_ALL_SKILLS = "SELECT * FROM competencias"

    private static final IConnectionProvider connectionProvider = ConnectionProviderFactory.createConnectionProvider(DatabaseType.POSTGRE)


    static void list() {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement allSkills = prepareAllStatement(connection, SEARCH_ALL_SKILLS)
            ResultSet resultSet = allSkills.executeQuery()

            int skillCount = getRowCount(resultSet)

            if (skillCount > 0) {
                textListSkill(resultSet)
                connectionProvider.disconnect()

            } else {
                println("Não existem compêtencias cadastradas")
            }

        } catch (Exception e) {
            handleExceptionDB(e, "listar")
        }
    }

    static void create(Skill skill) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement skillById = connection.prepareStatement(SEARCH_ID_SKILL)
            skillById.setString(1, skill.name)
            ResultSet resultSet = skillById.executeQuery()

            if (!resultSet.next()) {
                setSkillParameters(connection, skill, INSERT_SKILL)

                System.out.println("A competência " + skill.name + " foi inserida com sucesso.")
            } else {
                System.err.println("A competência " + skill.name + " já existe.")
            }

            skillById.close()
            connectionProvider.disconnect()

        } catch (Exception e) {
            handleExceptionDB(e, "criar")
        }
    }

    static void update(int id) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement skillById = prepareByIdStatement(id, connection, SEARCH_SKILL_BY_ID)
            ResultSet resultSet = skillById.executeQuery()

            int skillCount = getRowCount(resultSet)

            if (skillCount > 0) {
                clearConsole()

                Skill skill = inputsUpdateSkill()
                setSkillUpdateParameters(connection, skill, id, UPDATE_SKILL)

                clearConsole()
                System.out.println("Competência com ID " + id + " atualizada com sucesso.")
            } else {
                clearConsole()
                System.out.println("Não existe uma competência com o ID informado.")
            }
            connectionProvider.disconnect()
        } catch (SQLException e) {
            handleExceptionDB(e, "atualizar")
        }
    }

    static void delete(int id) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement skillByID = prepareByIdStatement(id, connection, SEARCH_SKILL_BY_ID)
            ResultSet resultSet = skillByID.executeQuery()

            int skillCount = getRowCount(resultSet)

            if (skillCount > 0) {
                prepareDeleteStatement(id, connection, DELETE_SKILL)
                clearConsole()
                print("A competência com ID " + id + " foi deletada com sucesso.")

            } else {
                clearConsole()
                println("Não há competência com o id informado.")

            }
        } catch (SQLException e) {
            handleExceptionDB(e, "deletar")
        }
    }
}