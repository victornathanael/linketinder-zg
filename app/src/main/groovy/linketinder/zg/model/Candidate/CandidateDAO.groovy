package linketinder.zg.model.Candidate

import linketinder.zg.db.ConnectionJDBC
import linketinder.zg.util.ClearConsole

import java.sql.*

class CandidateDAO {
    static void create(Candidate candidate) {
        ArrayList<String> arrayOfSkills = candidate.skills.split(',')

        String INSERIR_CANDIDATO = "INSERT INTO candidatos (nome, email, cpf, idade, estado, cep, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)"
        String INSERIR_COMPETENCIAS = "INSERT INTO competencias (nome) VALUES (?)"
        String VERIFICAR_COMPETENCIA = "SELECT id FROM competencias WHERE nome = ?"
        String INSERIR_VINCULO_CANDIDATO_COMPETENCIA = "INSERT INTO competencias_candidatos(candidato_id, competencia_id) VALUES (?, ?)"

        try {
            Connection connection = ConnectionJDBC.conectar()
            PreparedStatement salvarCandidato = connection.prepareStatement(INSERIR_CANDIDATO, Statement.RETURN_GENERATED_KEYS)
            PreparedStatement salvarCompetencia = connection.prepareStatement(INSERIR_COMPETENCIAS, Statement.RETURN_GENERATED_KEYS)
            PreparedStatement verificarCompetencia = connection.prepareStatement(VERIFICAR_COMPETENCIA)
            PreparedStatement vincularCandidatoCompetencia = connection.prepareStatement(INSERIR_VINCULO_CANDIDATO_COMPETENCIA)

            salvarCandidato.setString(1, candidate.name)
            salvarCandidato.setString(2, candidate.email)
            salvarCandidato.setString(3, candidate.cpf)
            salvarCandidato.setInt(4, candidate.age)
            salvarCandidato.setString(5, candidate.state)
            salvarCandidato.setString(6, candidate.cep)
            salvarCandidato.setString(7, candidate.personalDescription)

            salvarCandidato.executeUpdate()
            ResultSet generatedKeys = salvarCandidato.getGeneratedKeys()

            if (generatedKeys.next()) {
                int candidatoId = generatedKeys.getInt(1)

                if (!arrayOfSkills.isEmpty()) {
                    for (String skill : arrayOfSkills) {
                        verificarCompetencia.setString(1, skill.toLowerCase().trim())
                        ResultSet resultSet = verificarCompetencia.executeQuery()

                        int competenciaId
                        if (resultSet.next()) {
                            competenciaId = resultSet.getInt("id")
                        } else {
                            salvarCompetencia.setString(1, skill.toLowerCase().trim())
                            salvarCompetencia.executeUpdate()

                            ResultSet generatedCompetenciaKeys = salvarCompetencia.getGeneratedKeys()
                            if (generatedCompetenciaKeys.next()) {
                                competenciaId = generatedCompetenciaKeys.getInt(1)
                            } else {
                                throw new SQLException("Erro ao obter o ID da competência recém-adicionada.")
                            }
                        }

                        vincularCandidatoCompetencia.setInt(1, candidatoId)
                        vincularCandidatoCompetencia.setInt(2, competenciaId)
                        vincularCandidatoCompetencia.executeUpdate()
                    }
                }

                generatedKeys.close()
            }

            salvarCandidato.close()
            salvarCompetencia.close()
            verificarCompetencia.close()
            vincularCandidatoCompetencia.close()

            ConnectionJDBC.desconectar(connection)

            System.out.println("O candidato " + candidate.name + " foi inserido com sucesso.")
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro ao salvar candidato no banco de dados.")
            System.exit(-42)
        }
    }

    static void update(int id, Scanner input) {

        String BUSCAR_POR_ID = "SELECT * FROM candidatos WHERE id=?"
        try {
            Connection connection = ConnectionJDBC.conectar()
            PreparedStatement candidate = connection.prepareStatement(
                    BUSCAR_POR_ID,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            candidate.setInt(1, id);
            ResultSet res = candidate.executeQuery();

            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            if (qtd > 0) {
                ClearConsole.clearConsole()

                input.nextLine()

                print("Digite o novo nome: ")
                String novoNome = input.nextLine()

                print("Digite o novo email: ")
                String novoEmail = input.nextLine()

                print("Digite o novo CPF: ")
                String novoCpf = input.nextLine()

                print("Digite a nova idade: ")
                int novaIdade = input.nextInt()
                input.nextLine()

                print("Digite o novo estado: ")
                String novoEstado = input.nextLine()

                print("Digite o novo CEP: ")
                String novoCep = input.nextLine()

                print("Digite a nova descrição: ")
                String novaDescricao = input.nextLine()

                // Lógica para atualizar o banco de dados
                String UPDATE_CANDIDATO = "UPDATE candidatos SET nome=?, email=?, cpf=?, idade=?, estado=?, cep=?, descricao=? WHERE id=?"

                PreparedStatement atualizarCandidato = connection.prepareStatement(UPDATE_CANDIDATO)

                atualizarCandidato.setString(1, novoNome)
                atualizarCandidato.setString(2, novoEmail)
                atualizarCandidato.setString(3, novoCpf)
                atualizarCandidato.setInt(4, novaIdade)
                atualizarCandidato.setString(5, novoEstado)
                atualizarCandidato.setString(6, novoCep)
                atualizarCandidato.setString(7, novaDescricao)
                atualizarCandidato.setInt(8, id)

                atualizarCandidato.executeUpdate();

                ClearConsole.clearConsole()
                print("Candidato com ID " + id + " atualizado com sucesso.")
            } else {
                ClearConsole.clearConsole()
                println("Não existe um candidato com o id informado.")
            }
        } catch (SQLException e) {
            e.printStackTrace()
            print("Erro ao atualizar candidato no banco de dados.")
        }
    }

    static void delete(int id) {
        String BUSCAR_POR_ID = "SELECT * FROM candidatos WHERE id=?"
        String DELETE_CANDIDATO = "DELETE FROM candidatos WHERE id=?"

        try {
            Connection connection = ConnectionJDBC.conectar()
            PreparedStatement candidate = connection.prepareStatement(
                    BUSCAR_POR_ID,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            candidate.setInt(1, id);
            ResultSet res = candidate.executeQuery();

            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            if (qtd > 0) {
                PreparedStatement deletarCandidato = connection.prepareStatement(DELETE_CANDIDATO)

                deletarCandidato.setInt(1, id)
                deletarCandidato.executeUpdate()

                ClearConsole.clearConsole()
                print("Candidato com ID " + id + " deletado com sucesso.")

            } else {
                ClearConsole.clearConsole()
                println("Não existe um candidato com o id informado.")

            }
        } catch (SQLException e) {
            e.printStackTrace()
            print("Erro ao deletar candidato no banco de dados.")
        }
    }
}
