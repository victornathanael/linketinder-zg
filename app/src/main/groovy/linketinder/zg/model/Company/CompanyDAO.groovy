package linketinder.zg.model.Company

import linketinder.zg.db.factory.ConnectionProviderFactory
import linketinder.zg.db.factory.DatabaseType
import linketinder.zg.db.factory.IConnectionProvider
import linketinder.zg.model.Candidate.CandidateJson

import java.sql.*
import java.util.stream.Collectors

import static linketinder.zg.util.GetRowCount.getRowCount
import static linketinder.zg.util.HandleException.*
import static linketinder.zg.util.GetSkillId.*
import static linketinder.zg.util.PrepareStatement.*
import static linketinder.zg.util.LinkSkillWith.*
import static linketinder.zg.util.CompanyParameters.*

class CompanyDAO {
    private static final String INSERT_COMPANY = "INSERT INTO empresas (nome, email, cnpj, pais, estado, cep, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)"
    private static final String INSERT_SKILLS = "INSERT INTO competencias (nome) VALUES (?)"
    private static final String VERIFY_SKILL = "SELECT id FROM competencias WHERE nome = ?"
    private static final String INSERT_LINK_COMPANY_SKILL = "INSERT INTO competencias_empresas(empresa_id, competencia_id) VALUES (?, ?)"
    private static final String UPDATE_COMPANY = "UPDATE empresas SET nome=?, email=?, cnpj=?, pais=?, estado=?, cep=?, descricao=? WHERE id=?"
    private static final String DELETE_COMPANY = "DELETE FROM empresas WHERE id=?"
    private static final String SEARCH_COMPANY_BY_ID = "SELECT * FROM empresas WHERE id=?"
    private static final String SEARCH_ALL_COMPANIES = "SELECT c.id, c.nome, c.email, c.cnpj, c.pais, c.estado, c.cep, c.descricao, STRING_AGG(co.nome, ', ') AS competencias FROM empresas c LEFT JOIN competencias_empresas cc ON c.id = cc.empresa_id LEFT JOIN competencias co ON cc.competencia_id = co.id GROUP BY c.id"

    private static final IConnectionProvider connectionProvider = ConnectionProviderFactory.createConnectionProvider(DatabaseType.POSTGRE)

    static List<CompanyJson> list() {
        List<CompanyJson> companyJsonArrayList = new ArrayList<>()

        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement allCompanies = prepareAllStatement(connection, SEARCH_ALL_COMPANIES)
            ResultSet resultSet = allCompanies.executeQuery();

            int companyCount = getRowCount(resultSet)

            if (companyCount > 0) {
                while (resultSet.next()) {

                    CompanyJson companyJson = new CompanyJson()
                    companyJson.setId(resultSet.getInt("id"))
                    companyJson.setName(resultSet.getString("nome").trim())
                    companyJson.setCorporateEmail(resultSet.getString("email").trim())
                    companyJson.setCnpj(resultSet.getString("cnpj").trim())
                    companyJson.setCountry(resultSet.getString("pais"))
                    companyJson.setState(resultSet.getString("estado").trim())
                    companyJson.setCep(resultSet.getString("cep").trim())
                    companyJson.setCompanyDescription(resultSet.getString("descricao").trim())

                    String skillsString = resultSet.getString("competencias".trim())
                    List<String> skillsList = (skillsString != null)
                            ? Arrays.stream(skillsString.split(","))
                            .map(String::trim)
                            .collect(Collectors.toList())
                            : Collections.emptyList() as List<String>
                    companyJson.setSkills(skillsList)

                    companyJsonArrayList.add(companyJson)
                }
            } else {
                println("Não existem empresas cadastradas");
            }

        } catch (Exception e) {
            handleExceptionDB(e, "listar")
        } finally {
            connectionProvider.disconnect()
        }

        return companyJsonArrayList
    }

    static void create(Company company) {
        try (Connection connection = connectionProvider.connect()) {
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

                        linkSkillWith(linkCompanySkill, companyId, skillId)
                    }
                }
                generatedKeys.close()
            }

            saveCompany.close()
            saveSkill.close()
            verifySkill.close()
            linkCompanySkill.close()

            System.out.println("A empresa " + company.name + " foi inserido com sucesso.")
        } catch (Exception e) {
            handleExceptionDB(e, "criar")
        } finally {
            connectionProvider.disconnect()
        }
    }

    static void update(int id, Company company) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement companyById = prepareByIdStatement(id, connection, SEARCH_COMPANY_BY_ID)
            ResultSet resultSet = companyById.executeQuery()

            int companyCount = getRowCount(resultSet)

            if (companyCount > 0) {
                PreparedStatement updateCompany = connection.prepareStatement(UPDATE_COMPANY)
                setUpdateCompanyParameters(updateCompany, id, company)
                print("Empresa com ID " + id + " atualizada com sucesso.")
            } else {
                throw new Error("Não existe uma empresa com o id informado.")
            }
        } catch (SQLException e) {
            handleExceptionDB(e, "atualizar")
        } finally {
            connectionProvider.disconnect()
        }
    }

    static void delete(int id) {
        try (Connection connection = connectionProvider.connect()) {
            PreparedStatement companyById = prepareByIdStatement(id, connection, SEARCH_COMPANY_BY_ID)
            ResultSet resultSet = companyById.executeQuery();

            int companyCount = getRowCount(resultSet)

            if (companyCount > 0) {
                prepareDeleteStatement(id, connection, DELETE_COMPANY)
                println("Empresa com ID " + id + " deletado com sucesso.")

            } else {
                throw new Error("Não existe uma empresa com o id informado.")
            }
        } catch (SQLException e) {
            handleExceptionDB(e, "deletar")
        } finally {
            connectionProvider.disconnect()
        }
    }
}
