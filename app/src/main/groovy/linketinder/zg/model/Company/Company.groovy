package linketinder.zg.model.Company

class Company {
    private int id
    private String name
    private String corporateEmail
    private String cnpj
    private String country
    private String state
    private String cep
    private String companyDescription
    private List<String> skills

    String getId() {
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

    String getCorporateEmail() {
        return corporateEmail
    }

    void setCorporateEmail(String corporateEmail) {
        this.corporateEmail = corporateEmail
    }

    String getCnpj() {
        return cnpj
    }

    void setCnpj(String cnpj) {
        this.cnpj = cnpj
    }

    String getCountry() {
        return country
    }

    void setCountry(String country) {
        this.country = country
    }

    String getState() {
        return state
    }

    void setState(String state) {
        this.state = state
    }

    String getCep() {
        return cep
    }

    void setCep(String cep) {
        this.cep = cep
    }

    String getCompanyDescription() {
        return companyDescription
    }

    void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription
    }

    List<String> getSkills() {
        return skills
    }

    void setSkills(List<String> skills) {
        this.skills = skills
    }
}
