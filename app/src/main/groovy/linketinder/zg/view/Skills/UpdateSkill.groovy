package linketinder.zg.view.Skills

import linketinder.zg.model.Skill.Skill
import linketinder.zg.model.Skill.SkillDAO
import linketinder.zg.util.InputUtils

class UpdateSkill {
    static void updateSkill() {
        ListSkills.listSkills()
        Scanner input = new Scanner(System.in)
        print("Informe o id da competência : ");
        int id = input.nextInt();
        SkillDAO.update(id)
    }

    static Skill inputsUpdateSkill() {
        String name = InputUtils.getStringInput("Digite o novo nome: ")
        Skill skill = new Skill(name)
        return skill
    }

}
