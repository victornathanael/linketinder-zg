package view.Skills

import model.Skill.SkillDAO

class UpdateSkill {
    static void updateSkill() {
        ListSkills.listSkills()
        Scanner input = new Scanner(System.in)
        print("Informe o id da competência : ");
        int id = input.nextInt();
        SkillDAO.update(id, input)
    }
}
