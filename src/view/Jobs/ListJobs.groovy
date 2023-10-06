package view.Jobs

import db.ConexaoJDBC
import util.ClearConsole

import java.sql.*

class ListJobs {
    static void listJobs() {
        String BUSCAR_TODOS = "SELECT v.id, v.nome, v.descricao, e.nome AS empresa_nome FROM vagas v JOIN empresas e ON v.empresa_id = e.id"

        try {
            Connection connection = ConexaoJDBC.conectar()
            PreparedStatement vagas = connection.prepareStatement(
                    BUSCAR_TODOS,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet res = vagas.executeQuery()

            res.last()
            int qtd = res.getRow()
            res.beforeFirst()

            if (qtd > 0) {
                ClearConsole.clearConsole()
                println("Listando vagas...");
                println("-------------------------------");
                while (res.next()) {
                    println("ID: " + res.getInt("id"));
                    println("Nome: " + res.getString("nome"));
                    println("Descrição: " + res.getString("descricao"));
                    println("Empresa: " + res.getString("empresa_nome"));
                    println("-------------------------------");
                }
                ConexaoJDBC.desconectar(connection)

            } else {
                println("Não existem candidatos cadastrados")
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao buscar todos os candidatos.")
            System.exit(-42);
        }
    }
}

