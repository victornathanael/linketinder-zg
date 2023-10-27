package linketinder.zg

import linketinder.zg.db.ConnectionJDBC
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionJDBCTest {

    @Test
    void testConnect() {
        Connection connection = ConnectionJDBC.connect();

        assertNotNull(connection, "A conexão não deve ser nula");

        ConnectionJDBC.disconnect(connection);
    }

    @Test
    void testDisconnect() {
        Connection connection = ConnectionJDBC.connect();
        ConnectionJDBC.disconnect(connection);

        try {
            assertTrue(connection.isClosed(), "A conexão deve estar fechada após chamar desconectar");
        } catch (Exception ignored) {
            fail("Erro ao verificar o status da conexão após desconectar");
        }
    }
}
