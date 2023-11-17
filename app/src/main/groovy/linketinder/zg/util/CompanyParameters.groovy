package linketinder.zg.util

import linketinder.zg.model.Company.Company

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import static linketinder.zg.util.ConvertToListOfSkills.convertToListOfSkills

class CompanyParameters {
    static Company setListCompanyParameters(ResultSet resultSet) {
        Company company = new Company()
        company.setId(resultSet.getInt("id"))
        company.setName(resultSet.getString("nome").trim())
        company.setCorporateEmail(resultSet.getString("email").trim())
        company.setCnpj(resultSet.getString("cnpj").trim())
        company.setCountry(resultSet.getString("pais"))
        company.setState(resultSet.getString("estado").trim())
        company.setCep(resultSet.getString("cep").trim())
        company.setCompanyDescription(resultSet.getString("descricao").trim())

        String skillsString = resultSet.getString("competencias".trim())
        company.setSkills(convertToListOfSkills(skillsString))
        return company
    }

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

    static void setUpdateCompanyParameters(PreparedStatement updateCompany, int id, Company company) throws SQLException {
        updateCompany.setString(1, company.name)
        updateCompany.setString(2, company.corporateEmail)
        updateCompany.setString(3, company.cnpj)
        updateCompany.setString(4, company.country)
        updateCompany.setString(5, company.state)
        updateCompany.setString(6, company.cep)
        updateCompany.setString(7, company.companyDescription)
        updateCompany.setInt(8, id)

        updateCompany.executeUpdate()
    }

}
