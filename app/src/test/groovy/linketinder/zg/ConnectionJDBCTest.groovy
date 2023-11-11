package linketinder.zg


import linketinder.zg.db.factory.ConnectionProviderFactory
import linketinder.zg.db.factory.DatabaseType
import linketinder.zg.db.factory.IConnectionProvider
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import java.sql.Connection

import static org.junit.jupiter.api.Assertions.assertTrue
import static org.junit.jupiter.api.Assertions.fail

class ConnectionJDBCTest {
    private static Connection connection
    private static final IConnectionProvider connectionProvider = ConnectionProviderFactory.createConnectionProvider(DatabaseType.POSTGRE)

    @BeforeAll
    static void before() {
        connection = connectionProvider.connect()
    }

    @AfterAll
    static void after() {
        connectionProvider.disconnect()
    }

    @Test
    void testShouldCloseDB() {
        connectionProvider.disconnect()
        try {
            assertTrue(connection.isClosed(), "A conexão deve estar fechada após chamar desconectar")
        } catch (Exception ignored) {
            fail("Erro ao verificar o status da conexão após desconectar")
        }
    }
}
