package linketinder.zg.util

import java.sql.ResultSet
import java.sql.SQLException

static int getRowCount(ResultSet resultSet) throws SQLException {
    resultSet.last();
    int rowCount = resultSet.getRow();
    resultSet.beforeFirst();
    return rowCount;
}