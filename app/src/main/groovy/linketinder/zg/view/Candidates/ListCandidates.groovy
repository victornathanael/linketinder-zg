package linketinder.zg.view.Candidates

import linketinder.zg.model.Candidate.CandidateDAO
import linketinder.zg.util.ClearConsole

import java.sql.ResultSet


class ListCandidates {
    static void listCandidates() {
        CandidateDAO.list()
    }

    static void textListCandidate(ResultSet resultSet) {
        ClearConsole.clearConsole();
        println("Listando candidatos...");
        println("-------------------------------");
        while (resultSet.next()) {
            println("ID: " + resultSet.getInt("id"))
            println("Nome: " + resultSet.getString("nome"))
            println("Email: " + resultSet.getString("email"))
            println("CPF: " + resultSet.getString("cpf"))
            println("Competências: " + resultSet.getString("competencias"))
            println("-------------------------------")
    }}
}
