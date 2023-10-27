package linketinder.zg.view.Companies

import linketinder.zg.model.Candidate.Candidate
import linketinder.zg.model.Company.Company
import linketinder.zg.model.Company.CompanyDAO
import linketinder.zg.model.Skill.Skill
import linketinder.zg.util.InputUtils

class NewCompany {
    static void newCompany() {
        CompanyDAO.create(inputsNewCompany())
    }

    static Company inputsNewCompany() {
        String name = InputUtils.getStringInput("Digite o nome da nova empresa: ")
        String corporateEmail = InputUtils.getStringInput("Digite o email da nova empresa: ")
        String cnpj = InputUtils.getStringInput("Digite o CNPJ da nova empresa: ")
        String country = InputUtils.getStringInput("Digite o país da nova empresa: ")
        String state = InputUtils.getStringInput("Digite o estado da nova empresa: ")
        String cep = InputUtils.getStringInput("Digite o CEP da nova empresa: ")
        String companyDescription = InputUtils.getStringInput("Digite a descrição da nova empresa: ")
        List<Skill> skills = InputUtils.getSkillsInput("Digite as competências da nova empresa (ex: java, angular, groovy): ")

        Company company = new Company(name, corporateEmail, cnpj, country, state, cep, companyDescription, skills)
        return company
    }
}
