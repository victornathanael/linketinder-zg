package linketinder.zg.model.Skill

class Skill {
    private String name

    Skill(String name) {
        this.name = name
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    @Override
    String toString() {
        return name
    }
}

