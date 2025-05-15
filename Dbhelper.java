package com.example.firstproject;

import java.sql.*;

public class Dbhelper {



    public static Connection getConnection(){
        Connection conn ;
        String url = "jdbc:mysql://localhost:3306/restaurant";
        String username = "root";
        String password = "";
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static Connection closeConnection(Connection conn, PreparedStatement ps, ResultSet set){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(set != null){
            try {
                set.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }
}
