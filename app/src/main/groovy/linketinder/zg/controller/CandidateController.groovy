package linketinder.zg.controller

import com.google.gson.Gson
import linketinder.zg.model.Candidate.Candidate
import linketinder.zg.model.Candidate.CandidateDAO
import linketinder.zg.model.Candidate.CandidateJson

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/candidates")
class CandidateController extends HttpServlet {
    private Gson gson = new Gson()

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<CandidateJson> candidateJsonList = CandidateDAO.list()

            resp.setContentType("application/json")
            resp.getWriter().write(gson.toJson(candidateJsonList))
        } catch (Exception e) {
            handleException(resp, e, "Erro ao obter a lista de candidatos")
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Candidate candidate = readCandidateFromBody(req)

            if (isNullOrEmpty(candidate) || hasNullOrEmptyFields(candidate)) {
                sendBadRequestResponse(resp, "Campos obrigatórios não preenchidos")
                return
            }

            CandidateDAO.create(candidate)

            sendCreatedResponse(resp)
        } catch (Exception e) {
            handleException(resp, e, "Erro ao criar candidato")
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String candidateId = req.getParameter("id")
            Candidate candidate = readCandidateFromBody(req)

            if (isNullOrEmpty(candidate) || hasNullOrEmptyFields(candidate)) {
                sendBadRequestResponse(resp, "Campos obrigatórios não preenchidos")
                return
            }

            CandidateDAO.update(Integer.parseInt(candidateId), candidate)

            resp.getWriter().write(gson.toJson(candidate))

            sendOkResponse(resp)
        } catch (Exception e) {
            handleException(resp, e, "Erro ao atualizar candidato")
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String candidateId = req.getParameter("id")
            CandidateDAO.delete(Integer.parseInt(candidateId))

            resp.getWriter().write("Id " + candidateId + " deletado com sucesso")

            sendOkResponse(resp)
        } catch (Exception e) {
            handleException(resp, e, "Erro ao excluir candidato")
        }
    }

    private Candidate readCandidateFromBody(HttpServletRequest req) throws IOException {
        try (BufferedReader reader = req.getReader()) {
            StringBuilder jsonInput = new StringBuilder()
            String line

            while ((line = reader.readLine()) != null) {
                jsonInput.append(line)
            }

            return gson.fromJson(jsonInput.toString(), Candidate.class)
        }
    }

    private static void sendCreatedResponse(HttpServletResponse resp) {
        resp.setContentType("application/json")
        resp.setCharacterEncoding("UTF-8")
        resp.setStatus(HttpServletResponse.SC_CREATED)
    }

    private static void sendOkResponse(HttpServletResponse resp) {
        resp.setContentType("application/json")
        resp.setCharacterEncoding("UTF-8")
        resp.setStatus(HttpServletResponse.SC_OK)
    }

    private static void sendBadRequestResponse(HttpServletResponse resp, String message) throws IOException {
        resp.setCharacterEncoding("UTF-8")
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST)
        resp.getWriter().write(message)
    }

    private static boolean hasNullOrEmptyFields(Candidate candidate) {
        return isNullOrEmpty(candidate.getName())
                || isNullOrEmpty(candidate.getEmail())
                || isNullOrEmpty(candidate.getCpf())
    }

    private static void handleException(HttpServletResponse resp, Exception e, String errorMessage) throws IOException {
        e.printStackTrace()
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
        resp.getWriter().write(errorMessage)
    }

    private static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty()
    }

    private static boolean isNullOrEmpty(Object value) {
        return value == null
    }
}




