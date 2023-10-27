package linketinder.zg.util

import linketinder.zg.model.Skill.Skill

class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    static String getStringInput(String prompt) {
        print(prompt);
        return scanner.nextLine();
    }

    static int getIntInput(String prompt) {
        print(prompt);
        return scanner.nextInt();
    }

    static List<Skill> getSkillsInput(String prompt) {
        print(prompt)
        String skillsInput = scanner.nextLine()
        return skillsInput.split(',').collect { new Skill(it.trim()) }
    }
}
