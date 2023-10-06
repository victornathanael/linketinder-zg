package view

import controller.ConexaoJDBC
import util.ClearConsole

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class ListCompanies {
    static void listCompanies() {
        String BUSCAR_TODOS = "SELECT * FROM empresas";

        try {
            Connection connection = ConexaoJDBC.conectar()
            PreparedStatement empresas = connection.prepareStatement(
                    BUSCAR_TODOS,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet res = empresas.executeQuery();

            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            if (qtd > 0) {
                ClearConsole.clearConsole()
                println("Listando empresas...");
                println("-------------------------------");
                while (res.next()) {
                    println("ID: " + res.getInt(1));
                    println("Nome: " + res.getString(2));
                    println("Email: " + res.getString(3));
                    println("CNPJ: " + res.getString(4));
                    println("-------------------------------");
                }
                ConexaoJDBC.desconectar(connection)

            } else {
                println("Não existem empresas cadastrados");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao buscar todas as empresas.");
            System.exit(-42);
        }
    }
}
