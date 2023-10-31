package linketinder.zg

import static linketinder.zg.util.GetGenerateId.*
import static linketinder.zg.util.GetRowCount.*
import static linketinder.zg.util.GetSkillId.*

import java.sql.PreparedStatement

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;


class UtilTest {
    @Test
    void testGetRowCount() throws SQLException {
        ResultSet resultSetMock = mock(ResultSet.class);

        when(resultSetMock.last()).thenReturn(true);
        when(resultSetMock.getRow()).thenReturn(5);

        int rowCount = getRowCount(resultSetMock);

        verify(resultSetMock).last();
        verify(resultSetMock).getRow();
        verify(resultSetMock).beforeFirst();

        assertEquals(5, rowCount);
    }

    @Test
    void testGetGenerateId() throws SQLException {
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);

        ResultSet resultSetMock = mock(ResultSet.class);

        when(preparedStatementMock.getGeneratedKeys()).thenReturn(resultSetMock);

        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt(1)).thenReturn(23);

        int generatedId = getGenerateId(preparedStatementMock);

        verify(preparedStatementMock).getGeneratedKeys();
        verify(resultSetMock).next();
        verify(resultSetMock).getInt(1);

        assertEquals(23, generatedId);
    }

    @Test
    void testGetSkillId() throws SQLException {
        PreparedStatement verifySkillMock = mock(PreparedStatement.class);
        PreparedStatement saveSkillMock = mock(PreparedStatement.class);

        ResultSet resultSetMock = mock(ResultSet.class);

        when(verifySkillMock.executeQuery()).thenReturn(resultSetMock);

        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("id")).thenReturn(46);

        int skillId = getSkillId(verifySkillMock, saveSkillMock, "Java");

        verify(verifySkillMock).setString(1, "java");
        verify(verifySkillMock).executeQuery();
        verify(resultSetMock).next();
        verify(resultSetMock).getInt("id");

        assertEquals(46, skillId);
    }
}



