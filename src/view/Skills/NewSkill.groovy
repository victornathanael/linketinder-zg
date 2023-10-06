package view.Skills

import model.Skill.*

class NewSkill {
    static void newSkill() {
        Scanner input = new Scanner(System.in)

        print " Digite o nome da nova competência: "
        String name = input.nextLine()

        Skill skill = new Skill(name)
        SkillDAO.create(skill)
    }
}
