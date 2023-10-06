package view.Skills

import model.Skill.SkillDAO

class DeleteSkill {
    static void deleteSkill() {
        ListSkills.listSkills()
        Scanner input = new Scanner(System.in)
        print("Informe o id da competência: ");
        int id = input.nextInt();
        SkillDAO.delete(id)
    }
}
