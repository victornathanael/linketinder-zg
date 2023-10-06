package model

import db.ConexaoJDBC
import util.ClearConsole

import java.sql.*

class CompanyDAO {

    static void create(Company company) {
        ArrayList<String> arrayOfSkills = company.skills.split(',')

        String INSERIR_EMPRESAS = "INSERT INTO empresas (nome, email, cnpj, pais, estado, cep, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)"
        String INSERIR_COMPETENCIAS = "INSERT INTO competencias (nome) VALUES (?)"
        String VERIFICAR_COMPETENCIA = "SELECT id FROM competencias WHERE nome = ?"
        String INSERIR_VINCULO_EMPRESA_COMPETENCIA = "INSERT INTO competencias_empresas(empresa_id, competencia_id) VALUES (?, ?)"

        try {
            Connection connection = ConexaoJDBC.conectar()
            PreparedStatement salvarEmpresa = connection.prepareStatement(INSERIR_EMPRESAS, Statement.RETURN_GENERATED_KEYS)
            PreparedStatement salvarCompetencia = connection.prepareStatement(INSERIR_COMPETENCIAS, Statement.RETURN_GENERATED_KEYS)
            PreparedStatement verificarCompetencia = connection.prepareStatement(VERIFICAR_COMPETENCIA)
            PreparedStatement vincularEmpresaCompetencia = connection.prepareStatement(INSERIR_VINCULO_EMPRESA_COMPETENCIA)

            salvarEmpresa.setString(1, company.name)
            salvarEmpresa.setString(2, company.corporateEmail)
            salvarEmpresa.setString(3, company.cnpj)
            salvarEmpresa.setString(4, company.country)
            salvarEmpresa.setString(5, company.state)
            salvarEmpresa.setString(6, company.cep)
            salvarEmpresa.setString(7, company.companyDescription)

            salvarEmpresa.executeUpdate()

            ResultSet generatedKeys = salvarEmpresa.getGeneratedKeys()

            if (generatedKeys.next()) {
                int empresaId = generatedKeys.getInt(1)

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

                        vincularEmpresaCompetencia.setInt(1, empresaId)
                        vincularEmpresaCompetencia.setInt(2, competenciaId)
                        vincularEmpresaCompetencia.executeUpdate()
                    }
                }

                generatedKeys.close()
            }

            salvarEmpresa.close()
            salvarCompetencia.close()
            verificarCompetencia.close()
            vincularEmpresaCompetencia.close()

            ConexaoJDBC.desconectar(connection)

            System.out.println("A empresa " + company.name + " foi inserido com sucesso.")
        } catch (Exception e) {
            e.printStackTrace()
            System.err.println("Erro ao salvar a empresa no banco de dados.")
            System.exit(-42)
        }
    }

    static void update(int id, Scanner input) {
        input.nextLine()

        print (" Digite o novo nome: ")
        String name = input.nextLine()

        print("Digite o novo email: ")
        String corporateEmail = input.nextLine()

        print ("Digite o novo CNPJ: ")
        String cnpj = input.nextLine()

        print (" Digite o novo país: ")
        String country = input.nextLine()

        print " Digite o novo estado: "
        String state = input.nextLine()

        print " Digite o novo CEP: "
        String cep = input.nextLine()

        print " Digite a nova descrição: "
        String companyDescription = input.nextLine()

        // Lógica para atualizar o banco de dados
        String UPDATE_EMPRESA = "UPDATE empresas SET nome=?, email=?, cnpj=?, pais=?, estado=?, cep=?, descricao=? WHERE id=?"

        try (Connection connection = ConexaoJDBC.conectar()
             PreparedStatement atualizarEmpresa = connection.prepareStatement(UPDATE_EMPRESA)) {

            atualizarEmpresa.setString(1, name)
            atualizarEmpresa.setString(2, corporateEmail)
            atualizarEmpresa.setString(3, cnpj)
            atualizarEmpresa.setString(4, country)
            atualizarEmpresa.setString(5, state)
            atualizarEmpresa.setString(6, cep)
            atualizarEmpresa.setString(7, companyDescription)
            atualizarEmpresa.setInt(8, id)

            atualizarEmpresa.executeUpdate();

            ClearConsole.clearConsole()
            print("Empresa com ID " + id + " atualizada com sucesso.")

        } catch (SQLException e) {
            e.printStackTrace()
            print("Erro ao atualizar Empresa no banco de dados.")
        }
    }

    static void delete(int id) {
        String DELETE_EMPRESA = "DELETE FROM empresas WHERE id=?"
        String BUSCAR_POR_ID = "SELECT * FROM empresas WHERE id=?"

        try {
            Connection connection = ConexaoJDBC.conectar()
            PreparedStatement company = connection.prepareStatement(
                    BUSCAR_POR_ID,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            company.setInt(1, id);
            ResultSet res = company.executeQuery();

            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            if (qtd > 0) {
                PreparedStatement deletarEmpresa = connection.prepareStatement(DELETE_EMPRESA)

                deletarEmpresa.setInt(1, id)
                deletarEmpresa.executeUpdate()

                ClearConsole.clearConsole()
                print("Empresa com ID " + id + " deletado com sucesso.")

            } else {
                ClearConsole.clearConsole()
                println("Não existe uma empresa com o id informado.")

            }
        } catch (SQLException e) {
            e.printStackTrace()
            print("Erro ao deletar empresa no banco de dados.")
        }
    }
    
}
