package com.example.firstproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dishservice {

    public Dishes getDishByID(int id){
        Dishes dishes = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        conn = Dbhelper.getConnection();
        String query = "select * from dishes where id=?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            set = ps.executeQuery();
            while(set.next()){
                dishes = new Dishes(
                        set.getInt("id"),
                        set.getString("name"),
                        set.getInt("price"),
                        set.getBoolean("enable")


                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dishes;
    }

    public boolean updateDishes(Dishes dish){
        boolean condition = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        conn = Dbhelper.getConnection();
        String query = "update dishes set name=?,price=?,enable=? where id=?";
        try {
            ps = conn.prepareStatement(query);

            ps.setString(1,dish.getName());
            ps.setInt(2,dish.getPrice());
            ps.setBoolean(3,dish.isEnable());
            ps.setInt(4,dish.getId());
            int test = ps.executeUpdate();
            if(test == 1){
                condition = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Dbhelper.closeConnection(conn,ps,set);
        return condition;
    }

    public List<Dishes> getAllDishes(){
        List<Dishes> dishes = new ArrayList<>();
        Connection conn;
        conn = Dbhelper.getConnection();
        PreparedStatement ps ;
        ResultSet set;
        try {
            ps = conn.prepareStatement("select * from dishes where enable=true");
            set = ps.executeQuery();
            while(set.next()){
                dishes.add(new Dishes(set.getInt("id"),
                                       set.getString("name"),
                                       set.getInt("price"),
                                       set.getBoolean("enable")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Dbhelper.closeConnection(conn,ps,set);
        List<Dishes> dd = Mergesort.sortByName(dishes);
        return dd;
    }

    public List<Dishes> AllDishes(){
        List<Dishes> dishes = new ArrayList<>();
        Connection conn;
        conn = Dbhelper.getConnection();
        PreparedStatement ps ;
        ResultSet set;
        try {
            ps = conn.prepareStatement("select * from dishes");
            set = ps.executeQuery();
            while(set.next()){
                dishes.add(new Dishes(set.getInt("id"),
                        set.getString("name"),
                        set.getInt("price"),
                        set.getBoolean("enable")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Dbhelper.closeConnection(conn,ps,set);
        return dishes;
    }

    public boolean saveDishes(Dishes dish){
        boolean condition = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        conn = Dbhelper.getConnection();
        String query = "insert into dishes (name,price,enable) values (?,?,?)";
        try {
            ps = conn.prepareStatement(query);

            ps.setString(1,dish.getName());
            ps.setInt(2,dish.getPrice());
            ps.setBoolean(3,dish.isEnable());
            int test = ps.executeUpdate();
            if(test == 1){
                condition = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Dbhelper.closeConnection(conn,ps,set);
        return condition;
    }

    public boolean deleteDishes(int id){
        boolean condition = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        conn = Dbhelper.getConnection();
        String query = "delete from dishes where id=?";
        try {
            ps = conn.prepareStatement(query);


            ps.setInt(1,id);

            int test = ps.executeUpdate();
            if(test == 1){
                condition = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Dbhelper.closeConnection(conn,ps,set);
        return condition;
    }
}
