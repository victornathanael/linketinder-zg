package linketinder.zg.view.Companies

import linketinder.zg.model.Company.Company
import linketinder.zg.model.Company.CompanyDAO

import linketinder.zg.util.InputUtils

class UpdateCompany {
    static void updateCompany() {
        ListCompanies.listCompanies()
        Scanner input = new Scanner(System.in)
        print("Informe o id da empresa : ");
        int id = input.nextInt();
        CompanyDAO.update(id, input)
    }

    static Company inputsUpdateCompany() {
        String name = InputUtils.getStringInput("Digite o novo nome: ")
        String corporateEmail = InputUtils.getStringInput("Digite o novo email: ")
        String cnpj = InputUtils.getStringInput("Digite o novo CNPJ: ")
        String country = InputUtils.getStringInput("Digite o novo país: ")
        String state = InputUtils.getStringInput("Digite o novo estado: ")
        String cep = InputUtils.getStringInput("Digite o novo CEP: ")
        String companyDescription = InputUtils.getStringInput("Digite a nova descrição: ")

        Company updatedCompanyInputs = new Company(name, corporateEmail, cnpj, country, state, cep, companyDescription)

        return updatedCompanyInputs
    }

}
