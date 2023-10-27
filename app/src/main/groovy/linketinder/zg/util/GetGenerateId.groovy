package linketinder.zg.util

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

static int getGenerateId(PreparedStatement saveSkill) throws SQLException {
    ResultSet generatedKeys = saveSkill.getGeneratedKeys();
    if (generatedKeys.next()) {
        return generatedKeys.getInt(1);
    } else {
        throw new SQLException("Erro ao obter o ID da competência recém-adicionada.");
    }
}