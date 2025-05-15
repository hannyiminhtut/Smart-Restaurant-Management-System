package com.example.firstproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Adminservice {

    public boolean isAdminInclude(String name,String password){
        boolean conditon = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        conn =Dbhelper.getConnection();
        String query = "select * from admins where name=? and password=?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2,password);
            set = ps.executeQuery();
            if(set.next()){
                conditon = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Dbhelper.closeConnection(conn,ps,set);
        return conditon;
    }
}
