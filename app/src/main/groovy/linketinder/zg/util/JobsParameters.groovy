package linketinder.zg.util

import linketinder.zg.model.Job.Job

import java.sql.PreparedStatement

class JobsParameters {
    static void setJobParameters(PreparedStatement insertJob, Job jobs) {
        insertJob.setString(1, jobs.name)
        insertJob.setString(2, jobs.description)
        insertJob.setInt(3, jobs.idCompany)
        insertJob.executeUpdate()
        insertJob.close()
    }

    static void setUpdateJobParameters(PreparedStatement updateJob, Job jobs, int id) {
        updateJob.setString(1, jobs.name)
        updateJob.setString(2, jobs.description)
        updateJob.setInt(3, jobs.idCompany)
        updateJob.setInt(4, id)
        updateJob.executeUpdate()
        updateJob.close()
    }

}
