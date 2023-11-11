package linketinder.zg.controller

import com.google.gson.Gson
import linketinder.zg.model.Candidate.Candidate
import linketinder.zg.model.Candidate.CandidateDAO
import linketinder.zg.model.Candidate.CandidateJson
import linketinder.zg.model.Skill.Skill
import linketinder.zg.model.Skill.SkillDAO
import linketinder.zg.model.Skill.SkillJson

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
            List<SkillJson> skillJsonList = SkillDAO.list()

            resp.setContentType("application/json")

            String json = gson.toJson(skillJsonList)

            resp.getWriter().write(json)
        } catch (Exception e) {
            e.stackTrace
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Erro ao obter a lista de competências");
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

        Skill skill = gson.fromJson(jsonInput.toString(), Skill.class)

        if (isNullOrEmpty(skill) || isNullOrEmpty(skill.getName())) {
            resp.setCharacterEncoding("UTF-8")
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            resp.getWriter().write("Campos obrigatórios não preenchidos")
            return
        }

        SkillDAO.create(skill)

        resp.setContentType("application/json")
        resp.setCharacterEncoding("UTF-8")
        resp.setStatus(HttpServletResponse.SC_CREATED)
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String skillId = req.getParameter("id")

        StringBuilder jsonInput = new StringBuilder()
        try (BufferedReader reader = req.getReader()) {
            String line
            while ((line = reader.readLine()) != null) {
                jsonInput.append(line)
            }
        }

        Gson gson = new Gson();
        Skill skill = gson.fromJson(jsonInput.toString(), Skill.class)

        if (isNullOrEmpty(skill) || isNullOrEmpty(skill.getName())) {
            resp.setCharacterEncoding("UTF-8")
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            resp.getWriter().write("Campos obrigatórios não preenchidos")
            return
        }

        SkillDAO.update(skillId.toInteger(), skill)

        resp.getWriter().write(gson.toJson(skill))
        resp.setContentType("application/json")
        resp.setCharacterEncoding("UTF-8")
        resp.setStatus(HttpServletResponse.SC_CREATED)
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String skillId = req.getParameter("id");
        SkillDAO.delete(skillId.toInteger())


        resp.getWriter().write("Id " + skillId + " deletado com sucesso")
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
