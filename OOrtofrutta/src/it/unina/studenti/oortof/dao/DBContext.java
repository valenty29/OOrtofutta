package it.unina.studenti.oortof.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBContext {
    private String url;
    private String username;
    private String password;
    public DBContext()
    {
    	Properties props = new Properties();
        try (InputStream is = getClass().getResourceAsStream("resources/properties/db.properties")) {
          props.load(is);
        }
        catch (IOException e) {
          throw new RuntimeException("Exception loading db.properties");
        }
        url = props.getProperty("dburl");
        username = props.getProperty("dbuser");
        password = props.getProperty("dbpasswd");
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
