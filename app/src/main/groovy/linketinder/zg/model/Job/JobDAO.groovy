package linketinder.zg.model.Job

import linketinder.zg.db.factory.ConnectionProviderFactory
import linketinder.zg.db.factory.DatabaseType
import linketinder.zg.db.factory.IConnectionProvider

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import static linketinder.zg.util.GetRowCount.getRowCount
import static linketinder.zg.util.HandleExceptionDB.handleExceptionDB
import static linketinder.zg.util.JobsParameters.setJobParameters
import static linketinder.zg.util.JobsParameters.setUpdateJobParameters
import static linketinder.zg.util.PrepareStatement.*

class JobDAO {
    public static final String SEARCH_COMPANY = "SELECT id FROM empresas WHERE id = ?"
    public static final String INSERT_JOB = "INSERT INTO vagas (nome, descricao, empresa_id) VALUES (?, ?, ?)"
    public static final String UPDATE_JOB = "UPDATE vagas SET nome=?, descricao=?, empresa_id=? WHERE id=?"
    public static final String SEARCH_JOB_BY_ID = "SELECT * FROM vagas WHERE id=?"
    public static final String SEARCH_ALL_JOBS = "SELECT v.id, v.nome, v.descricao, v.empresa_id, e.nome AS empresa_nome FROM vagas v JOIN empresas e ON v.empresa_id = e.id"
    public static final String DELETE_JOB = "DELETE FROM vagas WHERE id=?"
    private static final IConnectionProvider connectionProvider = ConnectionProviderFactory.createConnectionProvider(DatabaseType.POSTGRE)

    static List<JobJson> list() {
        List<JobJson> jobJsonArrayList = new ArrayList<>()

        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement allJobs = prepareAllStatement(connection, SEARCH_ALL_JOBS)
            ResultSet resultSet = allJobs.executeQuery()

            int jobCount = getRowCount(resultSet)

            if (jobCount > 0) {
                while (resultSet.next()) {
                    JobJson jobJson = new JobJson()
                    jobJson.setId(resultSet.getInt("id"))
                    jobJson.setName(resultSet.getString("nome"))
                    jobJson.setDescription(resultSet.getString("descricao"))
                    jobJson.setIdCompany(resultSet.getInt("empresa_id"))
                    jobJson.setNameCompany(resultSet.getString("empresa_nome"))

                    jobJsonArrayList.add(jobJson)
                }
            } else {
                throw new Error("Não existem vagas cadastradas")
            }

        } catch (Exception e) {
            handleExceptionDB(e, "listar")
        } finally {
            connectionProvider.disconnect()
        }

        return jobJsonArrayList
    }

    static void create(Job job) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement insertJob = connection.prepareStatement(INSERT_JOB)
            PreparedStatement verifyCompany = connection.prepareStatement(SEARCH_COMPANY)
            verifyCompany.setInt(1, job.idCompany)
            ResultSet resultSet = verifyCompany.executeQuery()

            if (resultSet.next()) {
                setJobParameters(insertJob, job)

                println("A vaga " + job.name + " foi inserida com sucesso.")
            } else {
                throw new Error("A empresa com o ID " + job.idCompany + " não existe.")
            }

            verifyCompany.close()
        } catch (Exception e) {
            handleExceptionDB(e, "criar")
        } finally {
            connectionProvider.disconnect()
        }
    }

    static void update(int id, Job job) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement updateJob = connection.prepareStatement(UPDATE_JOB)
            PreparedStatement jobById = prepareByIdStatement(id, connection, SEARCH_JOB_BY_ID)
            PreparedStatement verifyCompany = connection.prepareStatement(SEARCH_COMPANY)

            verifyCompany.setInt(1, job.idCompany)
            ResultSet verifyResult = verifyCompany.executeQuery()

            if(!verifyResult.next()) {
                throw new Error("A empresa com o ID " + job.idCompany + " não existe.")
            }

            ResultSet resultSet = jobById.executeQuery()

            int jobCount = getRowCount(resultSet)

            if (jobCount > 0) {
                setUpdateJobParameters(updateJob, job, id)
                print("Vaga com ID " + id + " atualizado com sucesso.")
            } else {
                throw new Error("Não existe uma vaga com o id informado.")
            }
            verifyCompany.close()
        } catch (SQLException e) {
            handleExceptionDB(e, "atualizar")
        } finally {
            connectionProvider.disconnect()
        }
    }

    static void delete(int id) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement jobById = prepareByIdStatement(id, connection, SEARCH_JOB_BY_ID)
            ResultSet resultSet = jobById.executeQuery()

            int jobCount = getRowCount(resultSet)

            if (jobCount > 0) {
                prepareDeleteStatement(id, connection, DELETE_JOB)
                print("A vaga com ID " + id + " deletado com sucesso.")
            } else {
                throw new Error("Não há vaga com o id informado.")
            }
        } catch (SQLException e) {
            handleExceptionDB(e, "deletar")
        } finally {
            connectionProvider.disconnect()
        }
    }


}
