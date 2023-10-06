package view.Companies

import db.ConexaoJDBC
import util.ClearConsole

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class ListCompanies {
    static void listCompanies() {
        String BUSCAR_TODOS = "SELECT c.id, c.nome, c.email, c.cnpj, STRING_AGG(co.nome, ', ') AS competencias FROM empresas c LEFT JOIN competencias_empresas cc ON c.id = cc.empresa_id LEFT JOIN competencias co ON cc.competencia_id = co.id GROUP BY c.id"

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
                    println("ID: " + res.getInt("id"));
                    println("Nome: " + res.getString("nome"));
                    println("Email: " + res.getString("email"));
                    println("CNPJ: " + res.getString("cnpj"));
                    println("Competências: " + res.getString("competencias"))
                    println("-------------------------------");
                }
                ConexaoJDBC.desconectar(connection)

            } else {
                println("Não existem empresas cadastradas");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao buscar todas as empresas.");
            System.exit(-42);
        }
    }
}
