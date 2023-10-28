package linketinder.zg.util

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class PrepareStatement {

    static PreparedStatement prepareAllStatement(Connection connection, String QUERY) throws SQLException {
        return connection.prepareStatement(
                QUERY,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        )
    }

    static PreparedStatement prepareByIdStatement(int id, Connection connection, String QUERY) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
        preparedStatement.setInt(1, id)
        return preparedStatement
    }

    static void prepareDeleteStatement(int id, Connection connection, String QUERY) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY)
        preparedStatement.setInt(1, id)
        preparedStatement.executeUpdate()
    }
}
