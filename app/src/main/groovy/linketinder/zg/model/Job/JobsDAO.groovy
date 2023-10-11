package linketinder.zg.model.Job

import linketinder.zg.db.ConnectionJDBC

import linketinder.zg.util.ClearConsole

import java.sql.*

class JobsDAO {
    static void create(Jobs jobs) {

        String BUSCAR_EMPRESA = "SELECT id FROM empresas WHERE id = ?";
        String INSERIR_VAGA = "INSERT INTO vagas (nome, descricao, empresa_id) VALUES (?, ?, ?)";

        try {
            Connection connection = ConnectionJDBC.conectar();

            // Verificar se a empresa com o ID existe
            PreparedStatement verificarEmpresa = connection.prepareStatement(BUSCAR_EMPRESA);
            verificarEmpresa.setInt(1, jobs.idEmpresa);
            ResultSet resultado = verificarEmpresa.executeQuery();

            if (resultado.next()) {
                // Se a empresa existe, inserir a vaga
                PreparedStatement salvarVaga = connection.prepareStatement(INSERIR_VAGA);
                salvarVaga.setString(1, jobs.name);
                salvarVaga.setString(2, jobs.description);
                salvarVaga.setInt(3, jobs.idEmpresa);

                salvarVaga.executeUpdate();
                salvarVaga.close();

                System.out.println("A vaga " + jobs.name + " foi inserida com sucesso.");
            } else {
                System.err.println("A empresa com o ID " + jobs.idEmpresa + " não existe.");
            }

            verificarEmpresa.close();
            ConnectionJDBC.desconectar(connection);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao salvar vaga no banco de dados.");
            System.exit(-42);
        }
    }

    static void update(int id, Scanner input) {
        String BUSCAR_POR_ID = "SELECT * FROM vagas WHERE id=?"
        
        try {
            Connection connection = ConnectionJDBC.conectar()
            PreparedStatement job = connection.prepareStatement(
                    BUSCAR_POR_ID,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )

            job.setInt(1, id);
            ResultSet res = job.executeQuery();

            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            if (qtd > 0) {
                ClearConsole.clearConsole()

                input.nextLine()

                print("Digite o novo nome: ")
                String novoNome = input.nextLine()

                print("Digite a nova descrição: ")
                String novaDescricao = input.nextLine()

                // Lógica para atualizar o banco de dados
                String UPDATE_CANDIDATO = "UPDATE vagas SET nome=?, descricao=? WHERE id=?"

                PreparedStatement atualizarVaga = connection.prepareStatement(UPDATE_CANDIDATO)

                atualizarVaga.setString(1, novoNome)
                atualizarVaga.setString(2, novaDescricao)
                atualizarVaga.setInt(3, id)

                atualizarVaga.executeUpdate();

                ClearConsole.clearConsole()
                print("Vaga com ID " + id + " atualizado com sucesso.")
            } else {
                ClearConsole.clearConsole()
                println("Não existe uma vaga com o id informado.")
            }
        } catch (SQLException e) {
            e.printStackTrace()
            print("Erro ao atualizar vaga no banco de dados.")
        }
    }

    static void delete(int id) {
        String BUSCAR_POR_ID = "SELECT * FROM vagas WHERE id=?"
        String DELETE_CANDIDATO = "DELETE FROM vagas WHERE id=?"

        try {
            Connection connection = ConnectionJDBC.conectar()
            PreparedStatement job = connection.prepareStatement(
                    BUSCAR_POR_ID,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            job.setInt(1, id);
            ResultSet res = job.executeQuery();

            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            if (qtd > 0) {
                PreparedStatement deletarVaga = connection.prepareStatement(DELETE_CANDIDATO)

                deletarVaga.setInt(1, id)
                deletarVaga.executeUpdate()

                ClearConsole.clearConsole()
                print("A vaga com ID " + id + " deletado com sucesso.")

            } else {
                ClearConsole.clearConsole()
                println("Não há vaga com o id informado.")

            }
        } catch (SQLException e) {
            e.printStackTrace()
            print("Erro ao deletar a vaga no banco de dados.")
        }
    }
}
