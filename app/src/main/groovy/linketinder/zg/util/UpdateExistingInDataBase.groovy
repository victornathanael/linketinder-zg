package linketinder.zg.util

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

import static linketinder.zg.util.CandidateParameters.setUpdateCandidateParameters
import static linketinder.zg.util.ClearConsole.clearConsole
import static linketinder.zg.util.CompanyParameters.*

class UpdateExistingInDataBase {

    static void updateExistingCandidateInDataBase(int id, Connection connection, String QUERY) throws SQLException {
        PreparedStatement updateCandidate = connection.prepareStatement(QUERY)
        setUpdateCandidateParameters(updateCandidate, id)
        updateCandidate.executeUpdate()

        clearConsole()
        print("Candidato com ID " + id + " atualizado com sucesso.")
    }


    static void updateExistingCompanyInDataBase(int id, Connection connection, String QUERY) throws SQLException {
        PreparedStatement updateCompany = connection.prepareStatement(QUERY)
        setUpdateCompanyParameters(updateCompany, id)
        updateCompany.executeUpdate();

        clearConsole()
        print("Empresa com ID " + id + " atualizada com sucesso.")
    }

}
