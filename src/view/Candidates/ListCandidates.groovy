package view.Candidates

import db.ConexaoJDBC
import util.ClearConsole

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class ListCandidates {
    static void listCandidates() {
        String BUSCAR_TODOS = "SELECT c.id, c.nome, c.email, c.cpf, STRING_AGG(co.nome, ', ') AS competencias FROM candidatos c LEFT JOIN competencias_candidatos cc ON c.id = cc.candidato_id LEFT JOIN competencias co ON cc.competencia_id = co.id GROUP BY c.id"

        try {
            Connection connection = ConexaoJDBC.conectar()
            PreparedStatement candidatos = connection.prepareStatement(
                    BUSCAR_TODOS,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet res = candidatos.executeQuery()

            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            if (qtd > 0) {
                ClearConsole.clearConsole();
                println("Listando candidatos...");
                println("-------------------------------");
                while (res.next()) {
                    println("ID: " + res.getInt("id"))
                    println("Nome: " + res.getString("nome"))
                    println("Email: " + res.getString("email"))
                    println("CPF: " + res.getString("cpf"))
                    println("Competências: " + res.getString("competencias"))
                    println("-------------------------------")
                }
                ConexaoJDBC.desconectar(connection)

            } else {
                println("Não existem candidatos cadastrados")
            }

        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro ao buscar todos os candidatos.")
            System.exit(-42)
        }
    }

}
