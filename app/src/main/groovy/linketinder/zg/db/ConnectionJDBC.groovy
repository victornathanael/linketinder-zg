package linketinder.zg.db

import java.sql.*

class ConnectionJDBC {
    private static final String url = "jdbc:postgresql://localhost:5432/linketinder"
    private static final String user = "geek"
    private static final String password = "university"

    static Connection connect() {
        try {
            Class.forName("org.postgresql.Driver")
            return DriverManager.getConnection(url, user, password)
        } catch (SQLException e) {
            e.printStackTrace()
            throw new RuntimeException("Erro ao conectar ao banco de dados", e)
        }
    }

    static void disconnect(Connection connection) {
        if (connection != null) {
            try {
                connection.close()
            } catch (SQLException e) {
                e.printStackTrace()
                throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", e)
            }
        }
    }
}
