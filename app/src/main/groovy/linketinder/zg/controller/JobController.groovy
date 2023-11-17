package linketinder.zg.controller

import com.google.gson.Gson
import linketinder.zg.model.Job.Job
import linketinder.zg.model.Job.JobDAO

import linketinder.zg.util.HandleException
import linketinder.zg.util.IsNullOrEmpty
import linketinder.zg.util.SendHTTPServletResponse

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/jobs")
class JobController extends HttpServlet {
    private Gson gson = new Gson()

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<Job> jobList = JobDAO.list()

            resp.setContentType("application/json")
            resp.getWriter().write(gson.toJson(jobList))
        } catch (Exception e) {
            HandleException.handleExceptionController(resp, e, "Erro ao obter a lista de vagas")
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Job job = readJobFromBody(req)

            if (IsNullOrEmpty.isNullOrEmpty(job) || hasNullOrEmptyFields(job)) {
                SendHTTPServletResponse.sendBadRequestResponse(resp,"Campos obrigatórios não preenchidos")
                return
            }

            JobDAO.create(job)

            SendHTTPServletResponse.sendCreatedResponse(resp)
        } catch (Exception e) {
            HandleException.handleExceptionController(resp, e, "Erro ao criar vaga")
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String jobId = req.getParameter("id")
            Job job = readJobFromBody(req)

            if (IsNullOrEmpty.isNullOrEmpty(job) || hasNullOrEmptyFields(job)) {
                SendHTTPServletResponse.sendBadRequestResponse(resp, "Campos obrigatórios não preenchidos")
                return
            }

            JobDAO.update(jobId.toInteger(), job)

            resp.getWriter().write(gson.toJson(job))

            SendHTTPServletResponse.sendOkResponse(resp)
        } catch (Exception e) {
            HandleException.handleExceptionController(resp, e, "Erro ao atualizar vaga")
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String jobId = req.getParameter("id")
            JobDAO.delete(jobId.toInteger())

            resp.getWriter().write("Id " + jobId + " deletado com sucesso")

            SendHTTPServletResponse.sendOkResponse(resp)
        } catch (Exception e) {
            HandleException.handleExceptionController(resp, e, "Erro ao excluir vaga")
        }
    }

    private Job readJobFromBody(HttpServletRequest req) throws IOException {
        try (BufferedReader reader = req.getReader()) {
            StringBuilder jsonInput = new StringBuilder()
            String line

            while ((line = reader.readLine()) != null) {
                jsonInput.append(line)
            }

            return gson.fromJson(jsonInput.toString(), Job.class)
        }
    }

    private static boolean hasNullOrEmptyFields(Job job) {
        return IsNullOrEmpty.isNullOrEmpty(job)
                || IsNullOrEmpty.isNullOrEmpty(job.getName())
                || IsNullOrEmpty.isNullOrEmpty(job.getDescription())
                || IsNullOrEmpty.isNullOrEmpty(job.getIdCompany())
    }
}
