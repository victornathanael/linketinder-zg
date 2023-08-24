package view


import model.CompanyDAO
import util.ClearConsole

class ListCompanies {
    static void listCompanies() {
        ClearConsole.clearConsole()
        CompanyDAO.companies.each { company ->
            println "Name: ${company.name}"
            println "Email: ${company.corporateEmail}"
            println "Competências: ${company.skills.join(', ')}\n"
        }
    }
}
