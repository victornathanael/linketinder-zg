package linketinder.zg.util

import linketinder.zg.model.Job.Job

import java.sql.Connection
import java.sql.PreparedStatement

class JobsParameters {
    static void setJobParameters(Connection connection, Job jobs, String QUERY) {
        PreparedStatement insertJob = connection.prepareStatement(QUERY)
        insertJob.setString(1, jobs.name)
        insertJob.setString(2, jobs.description)
        insertJob.setInt(3, jobs.idEmpresa)
        insertJob.executeUpdate()
        insertJob.close()
    }

    static void setUpdateJobParameters(Connection connection, Job jobs, int id, String QUERY) {
        PreparedStatement updateJob = connection.prepareStatement(QUERY)
        updateJob.setString(1, jobs.name)
        updateJob.setString(2, jobs.description)
        updateJob.setInt(3, id)
        updateJob.executeUpdate();
        updateJob.close()
    }

}
