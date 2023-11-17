package linketinder.zg.util

import java.util.stream.Collectors

static List<String> convertToListOfSkills(String skillsString) {
    List<String> skillsList = (skillsString != null)
            ? Arrays.stream(skillsString.split(","))
            .map((s) -> s.trim())
            .collect(Collectors.toList())
            : Collections.emptyList() as List<String>
    return skillsList
}