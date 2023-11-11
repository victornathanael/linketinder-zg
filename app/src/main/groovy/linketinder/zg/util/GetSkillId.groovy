package linketinder.zg.util

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import static linketinder.zg.util.GetGenerateId.getGenerateId

static int getSkillId(PreparedStatement verifySkill, PreparedStatement saveSkill, String skill) throws SQLException {
    int skillId;
    verifySkill.setString(1, skill.toLowerCase().trim())
    ResultSet resultSet = verifySkill.executeQuery()

    if (resultSet.next()) {
        skillId = resultSet.getInt("id")
    } else {
        saveSkill.setString(1, skill.toLowerCase().trim())
        saveSkill.executeUpdate()

        skillId = getGenerateId(saveSkill)
    }

    return skillId;
}