package linketinder.zg.model.Job

class Job {
    private String name
    private String description
    private int idCompany

    Job(String name, String description, int idCompany) {
        this.name = name
        this.description = description
        this.idCompany = idCompany
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
}


