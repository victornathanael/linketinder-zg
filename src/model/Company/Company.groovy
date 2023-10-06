package model.Company

class Company {
    String name
    String corporateEmail
    String cnpj
    String country
    String state
    String cep
    String companyDescription
    String skills

    Company(String name, String corporateEmail, String cnpj, String country, String state, String cep, String companyDescription, String skills) {
        this.name = name
        this.corporateEmail = corporateEmail
        this.cnpj = cnpj
        this.country = country
        this.state = state
        this.cep = cep
        this.companyDescription = companyDescription
        this.skills = skills
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

    String getSkills() {
        return skills
    }

    void setSkills(String skills) {
        this.skills = skills
    }


}
