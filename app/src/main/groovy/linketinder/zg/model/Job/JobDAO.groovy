package linketinder.zg.model.Job

import linketinder.zg.db.factory.ConnectionProviderFactory
import linketinder.zg.db.factory.DatabaseType
import linketinder.zg.db.factory.IConnectionProvider

import static linketinder.zg.util.ClearConsole.*
import static linketinder.zg.view.Jobs.ListJobs.*
import static linketinder.zg.view.Jobs.UpdateJob.*

import static linketinder.zg.util.HandleExceptionDB.*
import static linketinder.zg.util.PrepareStatement.*
import static linketinder.zg.util.GetRowCount.*
import static linketinder.zg.util.JobsParameters.*


import java.sql.*

class JobsDAO {
    public static final String SEARCH_COMPANY = "SELECT id FROM empresas WHERE id = ?"
    public static final String INSERT_JOB = "INSERT INTO vagas (nome, descricao, empresa_id) VALUES (?, ?, ?)"
    public static final String UPDATE_JOB = "UPDATE vagas SET nome=?, descricao=? WHERE id=?"
    public static final String SEARCH_JOB_BY_ID = "SELECT * FROM vagas WHERE id=?"
    public static final String SEARCH_ALL_JOBS = "SELECT v.id, v.nome, v.descricao, e.nome AS empresa_nome FROM vagas v JOIN empresas e ON v.empresa_id = e.id"
    public static final String DELETE_JOB = "DELETE FROM vagas WHERE id=?"
    private static final IConnectionProvider connectionProvider = ConnectionProviderFactory.createConnectionProvider(DatabaseType.POSTGRE)

    static void list() {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement allJobs = prepareAllStatement(connection, SEARCH_ALL_JOBS)
            ResultSet resultSet = allJobs.executeQuery()

            int jobCount = getRowCount(resultSet)

            if (jobCount > 0) {
                textListJob(resultSet)
                connectionProvider.disconnect()

            } else {
                println("Não existem vagas cadastradas")
            }

        } catch (Exception e) {
            handleExceptionDB(e, "listar")
        }
    }

    static void create(Job jobs) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement verifyCompany = connection.prepareStatement(SEARCH_COMPANY)
            verifyCompany.setInt(1, jobs.idEmpresa)
            ResultSet resultSet = verifyCompany.executeQuery()

            if (resultSet.next()) {
                setJobParameters(connection, jobs, INSERT_JOB)

                System.out.println("A vaga " + jobs.name + " foi inserida com sucesso.")
            } else {
                System.err.println("A empresa com o ID " + jobs.idEmpresa + " não existe.")
            }

            verifyCompany.close();
            connectionProvider.disconnect();

        } catch (Exception e) {
            handleExceptionDB(e, "criar")
        }
    }

    static void update(int id) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement jobById = prepareByIdStatement(id, connection, SEARCH_JOB_BY_ID)
            ResultSet resultSet = jobById.executeQuery()

            int jobCount = getRowCount(resultSet)

            if (jobCount > 0) {
                clearConsole()

                Job jobs = inputsUpdateJob(id)
                setUpdateJobParameters(connection, jobs, id, UPDATE_JOB)

                clearConsole()
                print("Vaga com ID " + id + " atualizado com sucesso.")
            } else {
                clearConsole()
                println("Não existe uma vaga com o id informado.")
            }
            connectionProvider.disconnect()
        } catch (SQLException e) {
            handleExceptionDB(e, "atualizar")
        }
    }

    static void delete(int id) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement jobById = prepareByIdStatement(id, connection, SEARCH_JOB_BY_ID)
            ResultSet resultSet = jobById.executeQuery()

            int jobCount = getRowCount(resultSet)

            if (jobCount > 0) {
                prepareDeleteStatement(id, connection, DELETE_JOB)
                clearConsole()
                print("A vaga com ID " + id + " deletado com sucesso.")

            } else {
                clearConsole()
                println("Não há vaga com o id informado.")

            }
        } catch (SQLException e) {
            handleExceptionDB(e, "deletar")
        }
    }


}
