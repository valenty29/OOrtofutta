package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private String url;
    private String username;
    private String password;
    public DBContext(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection OpenConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (SQLException e)
        {
            throw e;
        }
    }
}
