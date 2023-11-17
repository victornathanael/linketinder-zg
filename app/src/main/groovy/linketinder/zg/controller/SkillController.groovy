package linketinder.zg.controller

import com.google.gson.Gson
import linketinder.zg.model.Skill.Skill
import linketinder.zg.model.Skill.SkillDAO

import linketinder.zg.util.HandleException
import linketinder.zg.util.IsNullOrEmpty
import linketinder.zg.util.SendHTTPServletResponse

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/skills")
class SkillController extends HttpServlet {
    private Gson gson = new Gson()

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<Skill> skillList = SkillDAO.list()

            resp.setContentType("application/json")
            resp.getWriter().write(gson.toJson(skillList))
        } catch (Exception e) {
            HandleException.handleExceptionController(resp, e, "Erro ao obter a lista de competências");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Skill skill = readSkillFromBody(req)

            if (IsNullOrEmpty.isNullOrEmpty(skill) || hasNullOrEmptyFields(skill)) {
                SendHTTPServletResponse.sendBadRequestResponse(resp, "Campos obrigatórios não preenchidos")
                return
            }

            SkillDAO.create(skill)

            SendHTTPServletResponse.sendCreatedResponse(resp)
        } catch (Exception e) {
            HandleException.handleExceptionController(resp, e, "Erro ao criar competência")
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String skillId = req.getParameter("id")
            Skill skill = readSkillFromBody(req)

            if (IsNullOrEmpty.isNullOrEmpty(skill) || hasNullOrEmptyFields(skill)) {
                SendHTTPServletResponse.sendBadRequestResponse(resp, "Campos obrigatórios não preenchidos")
                return
            }

            SkillDAO.update(skillId.toInteger(), skill)

            resp.getWriter().write(gson.toJson(skill))

            SendHTTPServletResponse.sendOkResponse(resp)
        } catch (Exception e) {
            HandleException.handleExceptionController(resp, e, "Erro ao atualizar competências")
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String skillId = req.getParameter("id");
            SkillDAO.delete(skillId.toInteger())

            resp.getWriter().write("Id " + skillId + " deletado com sucesso")

            SendHTTPServletResponse.sendOkResponse(resp)
        } catch (Exception e) {
            HandleException.handleExceptionController(resp, e, "Erro ao excluir competência")
        }
    }

    private Skill readSkillFromBody(HttpServletRequest req) throws IOException {
        try (BufferedReader reader = req.getReader()) {
            StringBuilder jsonInput = new StringBuilder()

            String line;

            while ((line = reader.readLine()) != null) {
                jsonInput.append(line);
            }

            return gson.fromJson(jsonInput.toString(), Skill.class)
        }
    }

    private static boolean hasNullOrEmptyFields(Skill skill) {
        return IsNullOrEmpty.isNullOrEmpty(skill.getName())
    }

}
