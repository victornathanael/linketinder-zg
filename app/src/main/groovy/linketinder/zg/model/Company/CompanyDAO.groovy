package linketinder.zg.model.Company

import java.sql.*

import linketinder.zg.db.ConnectionJDBC
import static linketinder.zg.util.ClearConsole.*
import static linketinder.zg.util.GetRowCount.getRowCount
import static linketinder.zg.util.HandleExceptionDB.*
import static linketinder.zg.util.GetSkillId.*
import static linketinder.zg.util.PrepareStatement.*

import static linketinder.zg.view.Companies.ListCompanies.*
import static linketinder.zg.view.Companies.UpdateCompany.*

class CompanyDAO {
    private static final String INSERT_COMPANY = "INSERT INTO empresas (nome, email, cnpj, pais, estado, cep, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)"
    private static final String INSERT_SKILLS = "INSERT INTO competencias (nome) VALUES (?)"
    private static final String VERIFY_SKILL = "SELECT id FROM competencias WHERE nome = ?"
    private static final String INSERT_LINK_COMPANY_SKILL = "INSERT INTO competencias_empresas(empresa_id, competencia_id) VALUES (?, ?)"
    private static final String UPDATE_COMPANY = "UPDATE empresas SET nome=?, email=?, cnpj=?, pais=?, estado=?, cep=?, descricao=? WHERE id=?"
    private static final String DELETE_COMPANY = "DELETE FROM empresas WHERE id=?"
    private static final String SEARCH_COMPANY_BY_ID = "SELECT * FROM empresas WHERE id=?"
    private static final String SEARCH_ALL_COMPANIES = "SELECT c.id, c.nome, c.email, c.cnpj, STRING_AGG(co.nome, ', ') AS competencias FROM empresas c LEFT JOIN competencias_empresas cc ON c.id = cc.empresa_id LEFT JOIN competencias co ON cc.competencia_id = co.id GROUP BY c.id"

    static void list() {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement allCompanies = prepareAllStatement(connection, SEARCH_ALL_COMPANIES)
            ResultSet resultSet = allCompanies.executeQuery();
            allCompanies.close()

            int companyCount = getRowCount(resultSet)

            if (companyCount > 0) {
                textListCompanies(resultSet)
                ConnectionJDBC.disconnect(connection)

            } else {
                println("Não existem empresas cadastradas");
            }

        } catch (Exception e) {
            handleExceptionDB(e, "listar")
        }
    }

    static void create(Company company) {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement saveCompany = connection.prepareStatement(INSERT_COMPANY, Statement.RETURN_GENERATED_KEYS)
            PreparedStatement saveSkill = connection.prepareStatement(INSERT_SKILLS, Statement.RETURN_GENERATED_KEYS)
            PreparedStatement verifySkill = connection.prepareStatement(VERIFY_SKILL)
            PreparedStatement linkCompanySkill = connection.prepareStatement(INSERT_LINK_COMPANY_SKILL)

            setCompanyParameters(saveCompany, company)

            ResultSet generatedKeys = saveCompany.getGeneratedKeys()
            if (generatedKeys.next()) {
                int companyId = generatedKeys.getInt(1)

                if (!company.skills.isEmpty()) {
                    for (String skill : company.skills) {
                        int skillId = getSkillId(verifySkill, saveSkill, skill);

                        linkSkillToCompany(linkCompanySkill, companyId, skillId)
                    }
                }
                generatedKeys.close()
            }

            saveCompany.close()
            saveSkill.close()
            verifySkill.close()
            linkCompanySkill.close()

            ConnectionJDBC.disconnect(connection)

            System.out.println("A empresa " + company.name + " foi inserido com sucesso.")
        } catch (Exception e) {
            handleExceptionDB(e, "criar")
        }
    }

    static void update(int id) {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement companyById = prepareByIdStatement(id, connection, SEARCH_COMPANY_BY_ID)
            ResultSet resultSet = companyById.executeQuery()
            companyById.close()

            int companyCount = getRowCount(resultSet)

            if (companyCount > 0) {
                clearConsole()
                updateExistingCompany(id, connection)
            } else {
                clearConsole()
                println("Não existe uma empresa com o id informado.")
            }
        } catch (SQLException e) {
            handleExceptionDB(e, "atualizar")
        }
    }

    static void delete(int id) {
        try (Connection connection = ConnectionJDBC.connect()) {
            PreparedStatement companyById = prepareByIdStatement(id, connection, SEARCH_COMPANY_BY_ID)
            ResultSet resultSet = companyById.executeQuery();
            companyById.close()

            int companyCount = getRowCount(resultSet)

            if (companyCount > 0) {
                prepareDeleteStatement(id, connection, DELETE_COMPANY)
                clearConsole()
                print("Empresa com ID " + id + " deletado com sucesso.")

            } else {
                clearConsole()
                println("Não existe uma empresa com o id informado.")
            }
        } catch (SQLException e) {
            handleExceptionDB(e, "deletar")
        }
    }

    private static void updateExistingCompany(int id, Connection connection) throws SQLException {
        PreparedStatement updateCompany = connection.prepareStatement(UPDATE_COMPANY)
        setUpdateCompanyParameters(updateCompany, id)
        updateCompany.executeUpdate();

        clearConsole()
        print("Empresa com ID " + id + " atualizada com sucesso.")
    }

    private static void setCompanyParameters(PreparedStatement saveCompany, Company company) throws SQLException {
        saveCompany.setString(1, company.name)
        saveCompany.setString(2, company.corporateEmail)
        saveCompany.setString(3, company.cnpj)
        saveCompany.setString(4, company.country)
        saveCompany.setString(5, company.state)
        saveCompany.setString(6, company.cep)
        saveCompany.setString(7, company.companyDescription)

        saveCompany.executeUpdate()
    }

    private static void setUpdateCompanyParameters(PreparedStatement updateCompany, int id) throws SQLException {
        Company updatedCompanyInputs = inputsUpdateCompany()

        updateCompany.setString(1, updatedCompanyInputs.name)
        updateCompany.setString(2, updatedCompanyInputs.corporateEmail)
        updateCompany.setString(3, updatedCompanyInputs.cnpj)
        updateCompany.setString(4, updatedCompanyInputs.country)
        updateCompany.setString(5, updatedCompanyInputs.state)
        updateCompany.setString(6, updatedCompanyInputs.cep)
        updateCompany.setString(7, updatedCompanyInputs.companyDescription)
        updateCompany.setInt(8, id)
    }

    private static void linkSkillToCompany(PreparedStatement linkCompanySkill, int companyId, int skillId) throws SQLException {
        linkCompanySkill.setInt(1, companyId)
        linkCompanySkill.setInt(2, skillId)
        linkCompanySkill.executeUpdate()
    }
}
