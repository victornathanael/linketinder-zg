package linketinder.zg

import linketinder.zg.db.PostgreSQLConnection
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test;

import java.sql.Connection

import static org.junit.jupiter.api.Assertions.*;

class ConnectionJDBCTest {
    static Connection connection

    @BeforeAll
    static void before() {
        connection = PostgreSQLConnection.connect()
    }

    @AfterAll
    static void after() {
        PostgreSQLConnection.disconnect(connection)
    }

    @Test
    void testShouldCloseDB() {
        PostgreSQLConnection.disconnect(connection)
        try {
            assertTrue(connection.isClosed(), "A conexão deve estar fechada após chamar desconectar")
        } catch (Exception ignored) {
            fail("Erro ao verificar o status da conexão após desconectar")
        }
    }
}
