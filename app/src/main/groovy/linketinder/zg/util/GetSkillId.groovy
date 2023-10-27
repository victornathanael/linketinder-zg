package linketinder.zg.util

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import static linketinder.zg.util.GetGenerateId.getGenerateId
import static linketinder.zg.util.VerifyIfSkillExist.verifyIfSkillExist

static int getSkillId(PreparedStatement verifySkill, PreparedStatement saveSkill, String skill) throws SQLException {
    int skillId;

    ResultSet resultSet = verifyIfSkillExist(verifySkill, skill)

    if (resultSet.next()) {
        skillId = resultSet.getInt("id")
    } else {
        saveSkill.setString(1, skill.toLowerCase().trim())
        saveSkill.executeUpdate()

        skillId = getGenerateId(saveSkill)
    }

    return skillId;
}