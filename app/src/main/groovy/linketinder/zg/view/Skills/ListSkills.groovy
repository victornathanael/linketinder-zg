package linketinder.zg.view.Skills

import linketinder.zg.model.Skill.SkillDAO
import linketinder.zg.util.ClearConsole

import java.sql.ResultSet

class ListSkills {
    static void listSkills() {
        SkillDAO.list()
    }

    static void textListSkill(ResultSet resultSet) {
        ClearConsole.clearConsole();
        println("Listando competências...");
        println("-------------------------------");
        while (resultSet.next()) {
            println("ID: " + resultSet.getInt("id"))
            println("Nome: " + resultSet.getString("nome"))
            println("-------------------------------")
        }
    }
    
}

