package com.example.firstproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public boolean addOrder(Orders order) throws SQLException {
        boolean condition = false;
        Connection conn ;
        PreparedStatement ps;
        conn = Dbhelper.getConnection();
        String query = "insert into orders(table_id,dish_id,price,count) values(?,?,?,?)";
        ps = conn.prepareStatement(query);
        ps.setInt(1,order.getTable_id());
        ps.setInt(2,order.getDish_id());
        ps.setInt(3,order.getPrice());
        ps.setInt(4,order.getCount());
        int index = ps.executeUpdate();
        if(index == 1){
            condition = true;
        }
        return condition;
    }

    public List<ActiveOrderCheck> getOrderDetailByTableId(int id) throws SQLException {
        List<ActiveOrderCheck> orders = new ArrayList<>();
        String query = "SELECT \n"+
        "\td.name as name,\n"+
               "\to.count,\n"+
               "\t o.price\n"+
        "FROM\n"+
        "\torders as o\n"+
        "\tRIGHT JOIN\n"+
        "\tdishes as d\n"+
           "ON\n"+
        "\to.dish_id = d.id\n"+
        "WHERE\n"+
       "\to.table_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        conn = Dbhelper.getConnection();
        ps = conn.prepareStatement(query);
        ps.setInt(1,id);
        set = ps.executeQuery();
        while(set.next()){
            orders.add(new ActiveOrderCheck(id,
                    set.getString("name"),
                    set.getInt("price"),
                    set.getInt("count"), set.getInt("price") * set.getInt("count")));
        }
        Dbhelper.closeConnection(conn,ps,set);

     return orders;
    }

    public List<ActiveOrderCheck> getOrderBetweenDate(String startday,String endday) throws SQLException {
        List<ActiveOrderCheck> orders = new ArrayList<>();
        String query = "SELECT\n"+
        "\to.table_id,\n"+
        "\to.price,\n"+
                "\to.count,\n"+
                "\td.name\n"+
        "FROM\n"+
        "\tcashOutOrders as o\n"+
        "LEFT JOIN\n"+
        "\tdishes as d\n"+
                "ON\n"+
        "\to.dish_id = d.id\n"+
        "WHERE\n"+
        "\to.created_at\n"+
              " BETWEEN\n" +
        "\t ? AND ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        conn = Dbhelper.getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1,startday);
        ps.setString(2,endday);
        set = ps.executeQuery();
        while(set.next()){
            orders.add(new ActiveOrderCheck(
                    set.getInt("table_id"),
                    set.getString("name"),
                    set.getInt("price"),
                    set.getInt("count"), set.getInt("price") * set.getInt("count")));
        }
        Dbhelper.closeConnection(conn,ps,set);

        return orders;
    }

    public boolean deleteOrder(int t_id) throws SQLException {
        boolean condition = false;
        Connection conn ;
        PreparedStatement ps;
        conn = Dbhelper.getConnection();
        String query = "delete from orders where table_id=?";
        ps = conn.prepareStatement(query);
        ps.setInt(1,t_id);
        int index = ps.executeUpdate();
        if(index == 1){
            condition = true;
        }
        return condition;
    }

    public boolean activeOrderCheck(int id) throws SQLException {
        boolean condition = false;
        Connection conn ;
        PreparedStatement ps ;
        ResultSet set;
        conn = Dbhelper.getConnection();
        String query = "select * from orders where table_id=?";
        ps = conn.prepareStatement(query);
        ps.setInt(1,id);
        set = ps.executeQuery();
        if(set.next()){
            condition = true;
        }

        return condition;
    }


}
