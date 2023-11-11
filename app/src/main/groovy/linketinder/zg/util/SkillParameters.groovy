package linketinder.zg.util

import linketinder.zg.model.Skill.Skill

import java.sql.PreparedStatement

class SkillParameters {
    static void setSkillParameters(PreparedStatement insertSkill, Skill skill) {
        insertSkill.setString(1, skill.name)
        insertSkill.executeUpdate()
        insertSkill.close()
    }

    static void setSkillUpdateParameters(PreparedStatement updateSkill, Skill skill, int id) {
        updateSkill.setString(1, skill.name)
        updateSkill.setInt(2, id)
        updateSkill.executeUpdate()
    }

}
