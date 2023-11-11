package linketinder.zg.model.Skill

import linketinder.zg.db.factory.ConnectionProviderFactory
import linketinder.zg.db.factory.DatabaseType
import linketinder.zg.db.factory.IConnectionProvider

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import static linketinder.zg.util.GetRowCount.getRowCount
import static linketinder.zg.util.HandleExceptionDB.handleExceptionDB
import static linketinder.zg.util.PrepareStatement.*
import static linketinder.zg.util.SkillParameters.setSkillParameters
import static linketinder.zg.util.SkillParameters.setSkillUpdateParameters

class SkillDAO {
    public static final String SEARCH_ID_SKILL = "SELECT id FROM competencias WHERE nome = ?"
    public static final String INSERT_SKILL = "INSERT INTO competencias (nome) VALUES (?)"
    public static final String SEARCH_SKILL_BY_ID = "SELECT * FROM competencias WHERE id=?"
    public static final String UPDATE_SKILL = "UPDATE competencias SET nome=? WHERE id=?"
    public static final String DELETE_SKILL = "DELETE FROM competencias WHERE id=?"
    public static final String SEARCH_ALL_SKILLS = "SELECT * FROM competencias"

    private static final IConnectionProvider connectionProvider = ConnectionProviderFactory.createConnectionProvider(DatabaseType.POSTGRE)


    static List<SkillJson> list() {
        List<SkillJson> skillJsonArrayList = new ArrayList<>()

        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement allSkills = prepareAllStatement(connection, SEARCH_ALL_SKILLS)
            ResultSet resultSet = allSkills.executeQuery()

            int skillCount = getRowCount(resultSet)

            if (skillCount > 0) {
                while (resultSet.next()) {
                    SkillJson skillJson = new SkillJson()
                    skillJson.setId(resultSet.getInt("id"))
                    skillJson.setName(resultSet.getString("nome"))

                    skillJsonArrayList.add(skillJson)
                }

            } else {
                println("Não existem compêtencias cadastradas")
            }

        } catch (Exception e) {
            handleExceptionDB(e, "listar")
        } finally {
            connectionProvider.disconnect()
        }

        return skillJsonArrayList
    }

    static void create(Skill skill) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement insertSkill = connection.prepareStatement(INSERT_SKILL)
            PreparedStatement skillById = connection.prepareStatement(SEARCH_ID_SKILL)

            skillById.setString(1, skill.name)
            ResultSet resultSet = skillById.executeQuery()

            if (!resultSet.next()) {
                setSkillParameters(insertSkill, skill)
                println("A competência " + skill.name + " foi inserida com sucesso.")
            } else {
                throw new Error("A competência " + skill.name + " já existe.")
            }

            skillById.close()

        } catch (Exception e) {
            handleExceptionDB(e, "criar")
        } finally {
            connectionProvider.disconnect()
        }
    }

    static void update(int id, Skill skill) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement updateSkill = connection.prepareStatement(UPDATE_SKILL)
            PreparedStatement skillById = prepareByIdStatement(id, connection, SEARCH_SKILL_BY_ID)
            ResultSet resultSet = skillById.executeQuery()

            int skillCount = getRowCount(resultSet)

            if (skillCount > 0) {
                setSkillUpdateParameters(updateSkill, skill, id)

                clearConsole()
                System.out.println("Competência com ID " + id + " atualizada com sucesso.")
            } else {
                clearConsole()
                System.out.println("Não existe uma competência com o ID informado.")
            }
        } catch (SQLException e) {
            handleExceptionDB(e, "atualizar")
        } finally {
            connectionProvider.disconnect()
        }
    }

    static void delete(int id) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement skillByID = prepareByIdStatement(id, connection, SEARCH_SKILL_BY_ID)
            ResultSet resultSet = skillByID.executeQuery()

            int skillCount = getRowCount(resultSet)

            if (skillCount > 0) {
                prepareDeleteStatement(id, connection, DELETE_SKILL)
                print("A competência com ID " + id + " foi deletada com sucesso.")
            } else {
                clearConsole()
                throw new Error("Não há competência com o id informado.")

            }
        } catch (SQLException e) {
            handleExceptionDB(e, "deletar")
        } finally {
            connectionProvider.disconnect()
        }
    }
}