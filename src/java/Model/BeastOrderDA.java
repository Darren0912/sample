/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Ding L
 */
public class BeastOrderDA {

    private String host = "jdbc:derby://localhost:1527/TheBeast Database";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "BeastOrder";
    private Connection conn;
    private PreparedStatement stmt;
   

    public BeastOrderDA() {
        createConnection();
    }

    public void addRecord(BeastOrder order) {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, order.getOrderID());
            stmt.setString(2, order.getShoppingCartID());
            stmt.setString(3, order.getOrderStatus());
            stmt.setDouble(4, order.getDeliveryFee());

            stmt.executeUpdate();
        } catch (SQLException ex) {
       System.out.println("****ERROR: " + ex.getMessage());
        }
    }

    public BeastOrder getRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE OrderID = ?";
        BeastOrder order = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

            }
        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }
        return order;
    }

    public BeastOrder getCartRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE OrderID = ?";
        BeastOrder order = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                order = new BeastOrder(rs.getString("OrderID"), rs.getString("CartID"), rs.getString("OrderStatus"), rs.getDouble("DeliveryFee"));
            }
        } catch (SQLException ex) {
           System.out.println("****ERROR: " + ex.getMessage());
        }
        return order;
    }

    public ArrayList<BeastOrder> getAll() throws SQLException {
        String queryStr = "SELECT * FROM " + tableName;
        ResultSet rs;

        stmt = conn.prepareStatement(queryStr);
        rs = stmt.executeQuery();
        ArrayList<BeastOrder> orderList = new ArrayList<BeastOrder>();
        while (rs.next()) {
            BeastOrder order = new BeastOrder(rs.getString("OrderID"), rs.getString("CartID"), rs.getString("OrderStatus"), rs.getDouble("DeliveryFee"));
            orderList.add(order);
        }

        return orderList;
    }

    public void updateRecord(BeastOrder order) {
        try {
            String updateStr = "UPDATE " + tableName
                    + " SET CartID = ?, OrderStatus = ?, DeliveryFee = ?  WHERE OrderID = ?";

            stmt = conn.prepareStatement(updateStr);
            stmt.setString(1, order.getShoppingCartID());
            stmt.setString(2, order.getOrderStatus());
            stmt.setDouble(3, order.getDeliveryFee());
            stmt.setString(4, order.getOrderID());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("****ERROR: " + ex.getMessage());
        }
    }

    public BeastOrder getOrderRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE CartID = ?";
        BeastOrder order = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                order = new BeastOrder(rs.getString("OrderID"), rs.getString("CartID"), rs.getString("OrderStatus"), rs.getDouble("DeliveryFee"));
            }
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
        return order;
    }

    public ArrayList<BeastOrder> getOrderListRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE CartID=?";
        BeastOrder order = null;
        ArrayList<BeastOrder> orderList = new ArrayList<BeastOrder>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                order = new BeastOrder(rs.getString("OrderID"), rs.getString("CartID"), rs.getString("OrderStatus"), rs.getDouble("DeliveryFee"));

                orderList.add(order);
            }
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }

        return orderList;
    }

    public String generateID() {

        String queryStr = "SELECT count(*) as readcount FROM " + tableName;

        String id = null;
        int num = 0;
        try {
            stmt = conn.prepareStatement(queryStr);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            num = rs.getInt("readcount");

            num++;
            id = "O" + num;

        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
        return id;
    }

    public void deleteRecord(String id) {
        try {
            String deleteStr = "DELETE FROM " + tableName + " WHERE OrderID = ?";
            stmt = conn.prepareStatement(deleteStr);
            stmt.setString(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
    }

    private void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(host, user, password);
        } catch (Exception ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }
    }

    private void shutDown() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("****ERROR: " + ex.getMessage());
            }
        }
    }

}
