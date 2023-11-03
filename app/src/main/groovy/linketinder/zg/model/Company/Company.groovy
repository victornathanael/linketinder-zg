package linketinder.zg.model.Company

import linketinder.zg.model.Skill.Skill

class Company {
    private String name
    private String corporateEmail
    private String cnpj
    private String country
    private String state
    private String cep
    private String companyDescription
    private List<Skill> skills

    Company(String name, String corporateEmail, String cnpj, String country, String state, String cep, String companyDescription, List<Skill> skills) {
        this.name = name
        this.corporateEmail = corporateEmail
        this.cnpj = cnpj
        this.country = country
        this.state = state
        this.cep = cep
        this.companyDescription = companyDescription
        this.skills = skills
    }

    Company(String name, String corporateEmail, String cnpj, String country, String state, String cep, String companyDescription) {
        this.name = name
        this.corporateEmail = corporateEmail
        this.cnpj = cnpj
        this.country = country
        this.state = state
        this.cep = cep
        this.companyDescription = companyDescription
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

    List<Skill> getSkills() {
        return skills
    }

    void setSkills(List<Skill> skills) {
        this.skills = skills
    }
}
