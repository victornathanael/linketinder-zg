package linketinder.zg.model.Job

class Jobs {
    String name
    String description
    int idEmpresa

    Jobs(String name, String description, int idEmpresa) {
        this.name = name
        this.description = description
        this.idEmpresa = idEmpresa
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

    int getIdEmpresa() {
        return idEmpresa
    }

    void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa
    }
}


