package linketinder.zg.model.Job

class JobJson {
    private int id
    private String name
    private String description
    private int idCompany
    private String nameCompany

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

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }

    int getIdCompany() {
        return idCompany
    }

    void setIdCompany(int idCompany) {
        this.idCompany = idCompany
    }

    String getNameCompany() {
        return nameCompany
    }

    void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany
    }
}
