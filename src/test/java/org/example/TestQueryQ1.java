package org.example;

import io.github.tanejagagan.sql.commons.ConnectionPool;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestQueryQ1 {

    private static Connection connection;
    public record Row (int id, String name) {};

    public void createTableAndInsert() {
        String[] sqls = {
                "CREATE TABLE STUDENT ( id int, name string)",
                "INSERT INTO STUDENT VALUES (1, 'a'), (2, 'b')"
        };
        ConnectionPool.executeBatch(sqls);
    }

    @Test
    public void testRetrieval() throws SQLException {
        createTableAndInsert();
        String sql = "select * from student order by id";
        var result = retrieveData(sql);
        Row[] expected = { new Row(1, "a"), new Row(2, "b")};
        assertArrayEquals(expected, result.toArray());
    }

    public List<Row> retrieveData(String sql) throws SQLException {
        List<Row> rows = new ArrayList<>();
        try(Connection connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(sql);
            try(ResultSet rs = statement.getResultSet()){
                while (rs.next()){
                    var id = rs.getInt(1);
                    var name = rs.getString(2);
                    rows.add(new Row(id, name));
                }
            }
        }
        return rows;
    }
}
