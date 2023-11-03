package linketinder.zg.util

import linketinder.zg.model.Company.Company

import java.sql.PreparedStatement
import java.sql.SQLException

import static linketinder.zg.view.Companies.UpdateCompany.inputsUpdateCompany

class CompanyParameters {
    static void setCompanyParameters(PreparedStatement saveCompany, Company company) throws SQLException {
        saveCompany.setString(1, company.name)
        saveCompany.setString(2, company.corporateEmail)
        saveCompany.setString(3, company.cnpj)
        saveCompany.setString(4, company.country)
        saveCompany.setString(5, company.state)
        saveCompany.setString(6, company.cep)
        saveCompany.setString(7, company.companyDescription)

        saveCompany.executeUpdate()
    }

    static void setUpdateCompanyParameters(PreparedStatement updateCompany, int id) throws SQLException {
        Company updatedCompanyInputs = inputsUpdateCompany()

        updateCompany.setString(1, updatedCompanyInputs.name)
        updateCompany.setString(2, updatedCompanyInputs.corporateEmail)
        updateCompany.setString(3, updatedCompanyInputs.cnpj)
        updateCompany.setString(4, updatedCompanyInputs.country)
        updateCompany.setString(5, updatedCompanyInputs.state)
        updateCompany.setString(6, updatedCompanyInputs.cep)
        updateCompany.setString(7, updatedCompanyInputs.companyDescription)
        updateCompany.setInt(8, id)
    }

}
