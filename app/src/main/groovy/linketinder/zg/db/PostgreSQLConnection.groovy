package linketinder.zg.db

import linketinder.zg.db.factory.IConnectionProvider

import java.sql.*

class PostgreSQLConnection implements IConnectionProvider {
    private static final String url = "jdbc:postgresql://localhost:5432/linketinder"
    private static final String user = "geek"
    private static final String password = "university"
    private static Connection connection;

    @Override
    Connection connect() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password)
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao conectar ao banco de dados", e)
            }
        }
        return connection;
    }

    @Override
    void disconnect() {
        try {
            connection.close()
        } catch (SQLException e) {
            e.printStackTrace()
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", e)
        } finally {
            connection = null
        }
    }
}
