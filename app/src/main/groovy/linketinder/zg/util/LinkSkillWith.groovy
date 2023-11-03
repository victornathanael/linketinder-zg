package linketinder.zg.util

import java.sql.PreparedStatement
import java.sql.SQLException

static void linkSkillWith(PreparedStatement linkSkill, int id, int skillId) throws SQLException {
    linkSkill.setInt(1, id)
    linkSkill.setInt(2, skillId)
    linkSkill.executeUpdate()
}
