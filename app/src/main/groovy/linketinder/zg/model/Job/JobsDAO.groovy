package linketinder.zg.model.Job

import linketinder.zg.db.ConnectionJDBC

import static linketinder.zg.util.ClearConsole.*
import static linketinder.zg.view.Jobs.ListJobs.*
import static linketinder.zg.view.Jobs.UpdateJob.*

import static linketinder.zg.util.HandleExceptionDB.*
import static linketinder.zg.util.PrepareStatement.*
import static linketinder.zg.util.GetRowCount.*


import java.sql.*

class JobsDAO {
    public static final String SEARCH_COMPANY = "SELECT id FROM empresas WHERE id = ?"
    public static final String INSERT_JOB = "INSERT INTO vagas (nome, descricao, empresa_id) VALUES (?, ?, ?)"
    public static final String UPDATE_CANDIDATE = "UPDATE vagas SET nome=?, descricao=? WHERE id=?"
    public static final String SEARCH_JOB_BY_ID = "SELECT * FROM vagas WHERE id=?"
    public static final String SEARCH_ALL_JOBS = "SELECT v.id, v.nome, v.descricao, e.nome AS empresa_nome FROM vagas v JOIN empresas e ON v.empresa_id = e.id"
    public static final String DELETE_JOB = "DELETE FROM vagas WHERE id=?"

    static void list() {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement allJobs = prepareAllStatement(connection, SEARCH_ALL_JOBS)
            ResultSet resultSet = allJobs.executeQuery()
            allJobs.close()

            int jobCount = getRowCount(resultSet)

            if (jobCount > 0) {
                textListJob(resultSet)
                ConnectionJDBC.disconnect(connection)

            } else {
                println("Não existem vagas cadastradas")
            }

        } catch (Exception e) {
            handleExceptionDB(e, "listar")
        }
    }

    static void create(Jobs jobs) {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement verifyCompany = connection.prepareStatement(SEARCH_COMPANY)
            verifyCompany.setInt(1, jobs.idEmpresa)
            ResultSet resultSet = verifyCompany.executeQuery()

            if (resultSet.next()) {
                setJobParameters(connection, jobs)

                System.out.println("A vaga " + jobs.name + " foi inserida com sucesso.")
            } else {
                System.err.println("A empresa com o ID " + jobs.idEmpresa + " não existe.")
            }

            verifyCompany.close();
            ConnectionJDBC.disconnect(connection);

        } catch (Exception e) {
            handleExceptionDB(e, "criar")
        }
    }

    static void update(int id) {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement jobById = prepareByIdStatement(id, connection, SEARCH_JOB_BY_ID)
            ResultSet resultSet = jobById.executeQuery()
            jobById.close()

            int jobCount = getRowCount(resultSet)

            if (jobCount > 0) {
                clearConsole()

                Jobs jobs = inputsUpdateJob(id)
                setUpdateJobParameters(connection, jobs, id)

                clearConsole()
                print("Vaga com ID " + id + " atualizado com sucesso.")
            } else {
                clearConsole()
                println("Não existe uma vaga com o id informado.")
            }
        } catch (SQLException e) {
            handleExceptionDB(e, "atualizar")
        }
    }

    static void delete(int id) {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement jobById = prepareByIdStatement(id, connection, SEARCH_JOB_BY_ID)
            ResultSet resultSet = jobById.executeQuery()
            jobById.close()

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

    static void setJobParameters(Connection connection, Jobs jobs) {
        PreparedStatement insertJob = connection.prepareStatement(INSERT_JOB)
        insertJob.setString(1, jobs.name)
        insertJob.setString(2, jobs.description)
        insertJob.setInt(3, jobs.idEmpresa)
        insertJob.executeUpdate()
        insertJob.close()
    }

    static void setUpdateJobParameters(Connection connection, Jobs jobs, int id) {
        PreparedStatement atualizarVaga = connection.prepareStatement(UPDATE_CANDIDATE)
        atualizarVaga.setString(1, jobs.name)
        atualizarVaga.setString(2, jobs.description)
        atualizarVaga.setInt(3, id)
        atualizarVaga.executeUpdate();
        atualizarVaga.close()
    }

}
