package linketinder.zg.view.Jobs

import linketinder.zg.model.Job.JobsDAO
import linketinder.zg.util.ClearConsole

import java.sql.*

class ListJobs {
    static void listJobs() {
        JobsDAO.list()
    }

    static void textListJob(ResultSet resultSet) {
        ClearConsole.clearConsole()
        println("Listando vagas...");
        println("-------------------------------");
        while (resultSet.next()) {
            println("ID: " + resultSet.getInt("id"));
            println("Nome: " + resultSet.getString("nome"));
            println("Descrição: " + resultSet.getString("descricao"));
            println("Empresa: " + resultSet.getString("empresa_nome"));
            println("-------------------------------");
        }
    }
}

