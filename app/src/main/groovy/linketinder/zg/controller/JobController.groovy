package linketinder.zg.controller

import com.google.gson.Gson
import linketinder.zg.model.Job.Job
import linketinder.zg.model.Job.JobDAO
import linketinder.zg.model.Job.JobJson

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
            List<JobJson> jobJsonList = JobDAO.list()

            resp.setContentType("application/json")

            String json = gson.toJson(jobJsonList)

            resp.getWriter().write(json)
        } catch (Exception e) {
            e.stackTrace
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Erro ao obter a lista de vagas");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader()
        StringBuilder jsonInput = new StringBuilder()

        String line;

        while ((line = reader.readLine()) != null) {
            jsonInput.append(line);
        }

        Job job = gson.fromJson(jsonInput.toString(), Job.class)

        if (isNullOrEmpty(job) || isNullOrEmpty(job.getName()) || isNullOrEmpty(job.getDescription()) || isNullOrEmpty(job.idCompany)) {
            resp.setCharacterEncoding("UTF-8")
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            resp.getWriter().write("Campos obrigatórios não preenchidos")
            return
        }

        JobDAO.create(job)

        resp.setContentType("application/json")
        resp.setCharacterEncoding("UTF-8")
        resp.setStatus(HttpServletResponse.SC_CREATED)
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jobId = req.getParameter("id")

        StringBuilder jsonInput = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonInput.append(line);
            }
        }

        Gson gson = new Gson();
        Job job = gson.fromJson(jsonInput.toString(), Job.class);

        if (isNullOrEmpty(job) || isNullOrEmpty(job.getName()) || isNullOrEmpty(job.getDescription()) || isNullOrEmpty(job.idCompany)) {
            resp.setCharacterEncoding("UTF-8")
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            resp.getWriter().write("Campos obrigatórios não preenchidos")
            return
        }

        JobDAO.update(jobId.toInteger(), job)

        resp.getWriter().write(gson.toJson(job))
        resp.setContentType("application/json")
        resp.setCharacterEncoding("UTF-8")
        resp.setStatus(HttpServletResponse.SC_CREATED)
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jobId = req.getParameter("id");
        JobDAO.delete(jobId.toInteger())


        resp.getWriter().write("Id " + jobId + " deletado com sucesso")
        resp.setContentType("application/json")
        resp.setCharacterEncoding("UTF-8")
        resp.setStatus(HttpServletResponse.SC_CREATED)
    }


    private static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static boolean isNullOrEmpty(Object value) {
        return value == null;
    }
}
