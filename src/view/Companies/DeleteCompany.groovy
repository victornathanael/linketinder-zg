package view.Companies

import model.Company.CompanyDAO

class DeleteCompany {
    static void deleteCompany() {
        ListCompanies.listCompanies()
        Scanner input = new Scanner(System.in)
        print("Informe o id do candidato : ");
        int id = input.nextInt();
        CompanyDAO.delete(id)
    }
}
