package linketinder.zg.model.Skill

import linketinder.zg.db.ConnectionJDBC
import linketinder.zg.util.ClearConsole

import java.sql.*

class SkillDAO {
    static void create(Skill skill) {

        String BUSCAR_COMPETENCIA = "SELECT id FROM competencias WHERE nome = ?";
        String INSERIR_COMPETENCIA = "INSERT INTO competencias (nome) VALUES (?)";

        try {
            Connection connection = ConnectionJDBC.conectar();

            PreparedStatement verificarEmpresa = connection.prepareStatement(BUSCAR_COMPETENCIA);
            verificarEmpresa.setString(1, skill.name);
            ResultSet resultado = verificarEmpresa.executeQuery();

            if (!resultado.next()) {
                PreparedStatement salvarCompetencia = connection.prepareStatement(INSERIR_COMPETENCIA);
                salvarCompetencia.setString(1, skill.name);

                salvarCompetencia.executeUpdate();
                salvarCompetencia.close();

                System.out.println("A competência " + skill.name + " foi inserida com sucesso.");
            } else {
                System.err.println("A competência " + skill.name + " já existe.");
            }

            verificarEmpresa.close();
            ConnectionJDBC.desconectar(connection);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao salvar competência no banco de dados.");
            System.exit(-42);
        }
    }

    static void update(int id, Scanner input) {
        String BUSCAR_POR_ID = "SELECT * FROM competencias WHERE id=?"

        try {
            Connection connection = ConnectionJDBC.conectar()
            PreparedStatement skill = connection.prepareStatement(
                    BUSCAR_POR_ID,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            skill.setInt(1, id)
            ResultSet res = skill.executeQuery()

            res.last()
            int qtd = res.getRow()
            res.beforeFirst()

            if (qtd > 0) {
                ClearConsole.clearConsole()

                input.nextLine()

                System.out.print("Digite o novo nome: ")
                String novoNome = input.nextLine()

                // Lógica para atualizar o banco de dados
                String UPDATE_COMPETENCIA = "UPDATE competencias SET nome=? WHERE id=?"

                PreparedStatement atualizarCompetencia = connection.prepareStatement(UPDATE_COMPETENCIA)

                atualizarCompetencia.setString(1, novoNome)
                atualizarCompetencia.setInt(2, id)
                atualizarCompetencia.executeUpdate()

                ClearConsole.clearConsole()
                System.out.println("Competência com ID " + id + " atualizada com sucesso.")
            } else {
                ClearConsole.clearConsole()
                System.out.println("Não existe uma competência com o ID informado.")
            }

            skill.close()
            ConnectionJDBC.desconectar(connection)

        } catch (SQLException e) {
            e.printStackTrace()
            System.err.println("Erro ao atualizar a competência no banco de dados.")
        }
    }

    static void delete(int id) {
        String BUSCAR_POR_ID = "SELECT * FROM competencias WHERE id=?"
        String DELETE_COMPETENCIA    = "DELETE FROM competencias WHERE id=?"

        try {
            Connection connection = ConnectionJDBC.conectar()
            PreparedStatement skill = connection.prepareStatement(
                    BUSCAR_POR_ID,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            skill.setInt(1, id);
            ResultSet res = skill.executeQuery();

            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            if (qtd > 0) {
                PreparedStatement deletarCompetencia = connection.prepareStatement(DELETE_COMPETENCIA)

                deletarCompetencia.setInt(1, id)
                deletarCompetencia.executeUpdate()

                ClearConsole.clearConsole()
                print("A competência com ID " + id + " deletado com sucesso.")

            } else {
                ClearConsole.clearConsole()
                println("Não há competência com o id informado.")

            }
        } catch (SQLException e) {
            e.printStackTrace()
            print("Erro ao deletar a competência no banco de dados.")
        }
    }
}