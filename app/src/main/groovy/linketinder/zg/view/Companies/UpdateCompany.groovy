package linketinder.zg.view.Companies

import linketinder.zg.model.Company.CompanyDAO

class UpdateCompany {
    static void updateCompany() {
        ListCompanies.listCompanies()
        Scanner input = new Scanner(System.in)
        print("Informe o id da empresa : ");
        int id = input.nextInt();
        CompanyDAO.update(id, input)
    }
}
