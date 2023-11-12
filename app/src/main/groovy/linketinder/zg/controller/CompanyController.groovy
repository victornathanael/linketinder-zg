package linketinder.zg.controller

import com.google.gson.Gson
import linketinder.zg.model.Company.Company
import linketinder.zg.model.Company.CompanyDAO
import linketinder.zg.model.Company.CompanyJson
import linketinder.zg.util.HandleException
import linketinder.zg.util.IsNullOrEmpty
import linketinder.zg.util.SendHTTPServletResponse

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/companies")
class CompanyController extends HttpServlet {
    private Gson gson = new Gson()

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<CompanyJson> companyJsonList = CompanyDAO.list()

            resp.setContentType("application/json")
            resp.getWriter().write(gson.toJson(companyJsonList))

        } catch (Exception e) {
            HandleException.handleExceptionController(resp, e, "Erro ao obter a lista de empresas")
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Company company = readCandidateFromBody(req)

            if (IsNullOrEmpty.isNullOrEmpty(company) || hasNullOrEmptyFields(company)) {
                SendHTTPServletResponse.sendBadRequestResponse(resp, "Campos obrigatórios não preenchidos")
                return
            }

            CompanyDAO.create(company)

            SendHTTPServletResponse.sendCreatedResponse(resp)
        } catch (Exception e) {
            HandleException.handleExceptionController(resp, e, "Erro ao criar empresa")
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String companyId = req.getParameter("id")
            Company company = readCandidateFromBody(req)

            if (IsNullOrEmpty.isNullOrEmpty(company) || hasNullOrEmptyFields(company)) {
                SendHTTPServletResponse.sendBadRequestResponse(resp, "Campos obrigatórios não preenchidos")
                return
            }

            CompanyDAO.update(companyId.toInteger(), company)

            resp.getWriter().write(gson.toJson(company))

            sendOkResponse(resp)
        } catch (Exception e) {
            handleException(resp, e, "Erro ao atualizar candidato")
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String companyId = req.getParameter("id")

            CompanyDAO.delete(companyId.toInteger())


            resp.getWriter().write("Id " + companyId + " deletado com sucesso")

            SendHTTPServletResponse.sendOkResponse(resp)
        } catch (Exception e) {
            HandleException.handleExceptionController(resp, e, "Erro ao excluir empresa")
        }
    }

    private Company readCandidateFromBody(HttpServletRequest req) throws IOException {
        try (BufferedReader reader = req.getReader()) {
            StringBuilder jsonInput = new StringBuilder()
            String line

            while ((line = reader.readLine()) != null) {
                jsonInput.append(line)
            }

            return gson.fromJson(jsonInput.toString(), Company.class)
        }
    }

    private static boolean hasNullOrEmptyFields(Company company) {
        return IsNullOrEmpty.isNullOrEmpty(company.getName())
                || IsNullOrEmpty.isNullOrEmpty(company.getCorporateEmail())
                || IsNullOrEmpty.isNullOrEmpty(company.getCnpj())
    }

}
