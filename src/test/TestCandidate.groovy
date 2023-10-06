package test

import db.ConexaoJDBC
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConexaoJDBCTest {

    @Test
    void testConectar() {
        Connection connection = ConexaoJDBC.conectar();

        assertNotNull(connection, "A conexão não deve ser nula");

        ConexaoJDBC.desconectar(connection);
    }

    @Test
    void testDesconectar() {
        Connection connection = ConexaoJDBC.conectar();
        ConexaoJDBC.desconectar(connection);

        try {
            assertTrue(connection.isClosed(), "A conexão deve estar fechada após chamar desconectar");
        } catch (Exception ignored) {
            fail("Erro ao verificar o status da conexão após desconectar");
        }
    }
}
