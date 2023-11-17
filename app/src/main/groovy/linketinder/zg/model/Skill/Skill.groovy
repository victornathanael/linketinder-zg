package linketinder.zg.model.Skill

class Skill {
    private int id
    private String name

    int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
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

