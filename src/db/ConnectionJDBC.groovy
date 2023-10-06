package db

import java.sql.*

class ConexaoJDBC {
    static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/linketinder";
            String user = "geek";
            String password = "university";

            System.out.println("Conexão bem-sucedida!");
            return DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Verifique se o servidor está ativo.");
            System.exit(-42);
            return null;
        }
    }

    static void desconectar(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
