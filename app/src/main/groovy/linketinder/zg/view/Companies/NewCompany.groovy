package linketinder.zg.view.Companies

import linketinder.zg.model.Company.Company
import linketinder.zg.model.Company.CompanyDAO

class NewCompany {
    static void newCompany() {
        Scanner input = new Scanner(System.in)

        print " Digite o nome da nova empresa: "
        String name = input.nextLine()

        print " Digite o email da nova empresa: "
        String corporateEmail = input.nextLine()

        print " Digite o CNPJ da nova empresa: "
        String cnpj = input.nextLine()

        print " Digite o país da nova empresa: "
        String country = input.nextLine()

        print " Digite o estado da nova empresa: "
        String state = input.nextLine()

        print " Digite o CEP da nova empresa: "
        String cep = input.nextLine()

        print " Digite a descrição da nova empresa: "
        String companyDescription = input.nextLine()

        print " Digite as competências da nova empresa (ex: java, angular, groovy): "
        String skills = input.nextLine()

        Company company = new Company(name, corporateEmail, cnpj, country, state, cep, companyDescription, skills)
        CompanyDAO.create(company)
    }
}
