package linketinder.zg

import linketinder.zg.db.ConnectionJDBC
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConexaoJDBCTest {

    @Test
    void testConectar() {
        Connection connection = ConnectionJDBC.conectar();

        assertNotNull(connection, "A conexão não deve ser nula");

        ConnectionJDBC.desconectar(connection);
    }

    @Test
    void testDesconectar() {
        Connection connection = ConnectionJDBC.conectar();
        ConnectionJDBC.desconectar(connection);

        try {
            assertTrue(connection.isClosed(), "A conexão deve estar fechada após chamar desconectar");
        } catch (Exception ignored) {
            fail("Erro ao verificar o status da conexão após desconectar");
        }
    }
}
