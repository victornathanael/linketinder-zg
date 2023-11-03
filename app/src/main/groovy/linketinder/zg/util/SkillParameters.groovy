package linketinder.zg.util

import linketinder.zg.model.Skill.Skill

import java.sql.Connection
import java.sql.PreparedStatement

class SkillParameters {
    static void setSkillParameters(Connection connection, Skill skill, String QUERY) {
        PreparedStatement insertSkill = connection.prepareStatement(QUERY)
        insertSkill.setString(1, skill.name)

        insertSkill.executeUpdate()
        insertSkill.close()
    }

    static void setSkillUpdateParameters(Connection connection, Skill skill, int id, String QUERY) {
        PreparedStatement updateSkill = connection.prepareStatement(QUERY)
        updateSkill.setString(1, skill.name)
        updateSkill.setInt(2, id)
        updateSkill.executeUpdate()
    }

}
