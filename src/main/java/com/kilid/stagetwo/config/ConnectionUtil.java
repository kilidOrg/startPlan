package com.kilid.stagetwo.config;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionUtil  {
        public static Connection connection ;

    static {
        try {
            connection = getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final  String url="jdbc:postgresql://localhost:5432/kilid";
    private static final  String userName ="postgres";
    private static final  String password ="postgres";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url,userName ,password);
        return connection;
    }

    private void close() throws SQLException {
        connection.close();
    }


}
