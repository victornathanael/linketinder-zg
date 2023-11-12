package linketinder.zg.controller

import com.google.gson.Gson
import linketinder.zg.model.Candidate.Candidate
import linketinder.zg.model.Candidate.CandidateDAO
import linketinder.zg.model.Candidate.CandidateJson
import linketinder.zg.util.HandleException
import linketinder.zg.util.IsNullOrEmpty
import linketinder.zg.util.SendHTTPServletResponse

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
            HandleException.handleExceptionController(resp, e, "Erro ao obter a lista de candidatos")
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Candidate candidate = readCandidateFromBody(req)

            if (IsNullOrEmpty.isNullOrEmpty(candidate) || hasNullOrEmptyFields(candidate)) {
                SendHTTPServletResponse.sendBadRequestResponse(resp, "Campos obrigatórios não preenchidos")
                return
            }

            CandidateDAO.create(candidate)

            SendHTTPServletResponse.sendCreatedResponse(resp)
        } catch (Exception e) {
            HandleException.handleExceptionController(resp, e, "Erro ao criar candidato")
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String candidateId = req.getParameter("id")
            Candidate candidate = readCandidateFromBody(req)

            if (IsNullOrEmpty.isNullOrEmpty(candidate) || hasNullOrEmptyFields(candidate)) {
                SendHTTPServletResponse.sendBadRequestResponse(resp, "Campos obrigatórios não preenchidos")
                return
            }

            CandidateDAO.update(Integer.parseInt(candidateId), candidate)

            resp.getWriter().write(gson.toJson(candidate))

            SendHTTPServletResponse.sendOkResponse(resp)
        } catch (Exception e) {
            HandleException.handleExceptionController(resp, e, "Erro ao atualizar candidato")
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String candidateId = req.getParameter("id")
            CandidateDAO.delete(Integer.parseInt(candidateId))

            resp.getWriter().write("Id " + candidateId + " deletado com sucesso")

            SendHTTPServletResponse.sendOkResponse(resp)
        } catch (Exception e) {
            HandleException.handleExceptionController(resp, e, "Erro ao excluir candidato")
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

    private static boolean hasNullOrEmptyFields(Candidate candidate) {
        return IsNullOrEmpty.isNullOrEmpty(candidate.getName())
                || IsNullOrEmpty.isNullOrEmpty(candidate.getEmail())
                || IsNullOrEmpty.isNullOrEmpty(candidate.getCpf())
    }
}




