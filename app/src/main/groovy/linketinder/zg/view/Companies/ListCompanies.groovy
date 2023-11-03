package linketinder.zg.view.Companies


import linketinder.zg.model.Company.CompanyDAO
import linketinder.zg.util.ClearConsole

import java.sql.ResultSet

class ListCompanies {
    static void listCompanies() {
        CompanyDAO.list()
    }

    static void textListCompanies(ResultSet resultSet) {
        ClearConsole.clearConsole()
        println("Listando empresas...");
        println("-------------------------------");
        while (resultSet.next()) {
            println("ID: " + resultSet.getInt("id"));
            println("Nome: " + resultSet.getString("nome"));
            println("Email: " + resultSet.getString("email"));
            println("CNPJ: " + resultSet.getString("cnpj"));
            println("Competências: " + resultSet.getString("competencias"))
            println("-------------------------------");
        }
    }

}
