package linketinder.zg.util

import java.sql.PreparedStatement
import java.sql.ResultSet

 static ResultSet verifyIfSkillExist(PreparedStatement verifySkill, String skill) {
    verifySkill.setString(1, skill.toLowerCase().trim())
    return verifySkill.executeQuery()
}