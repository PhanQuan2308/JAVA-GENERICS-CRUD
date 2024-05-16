package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class DbConnection {
    public static Connection  getConnection() throws SQLException {
        Connection conn = null;
        String dbURL = "jdbc:mysql://localhost:3306/nhom1";
        conn = DriverManager.getConnection(dbURL,"root","");
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        if(getConnection()!= null){
            System.out.printf("Connected");
        }
    }
}
