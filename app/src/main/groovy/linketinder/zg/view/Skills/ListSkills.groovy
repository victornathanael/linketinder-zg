package linketinder.zg.view.Skills

import linketinder.zg.db.ConnectionJDBC
import linketinder.zg.util.ClearConsole

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class ListSkills {
    static void listSkills() {
        String BUSCAR_TODOS = "SELECT * FROM competencias"

        try {
            Connection connection = ConnectionJDBC.conectar()
            PreparedStatement competencias = connection.prepareStatement(
                    BUSCAR_TODOS,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet res = competencias.executeQuery()

            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            if (qtd > 0) {
                ClearConsole.clearConsole();
                println("Listando competências...");
                println("-------------------------------");
                while (res.next()) {
                    println("ID: " + res.getInt("id"))
                    println("Nome: " + res.getString("nome"))
                    println("-------------------------------")
                }
                ConnectionJDBC.desconectar(connection)

            } else {
                println("Não existem compêtencias cadastrados")
            }

        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro ao buscar todas as competências.")
            System.exit(-42)
        }
    }
    }

