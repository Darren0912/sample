    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Order_Product;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Ding L
 */
public class Order_ProductDA {

    private String host = "jdbc:derby://localhost:1527/TheBeast Database";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Order_Product";
    private Connection conn;
    private PreparedStatement stmt;

    public Order_ProductDA() {
        createConnection();
    }

    public void addRecord(Order_Product op) {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, op.getOrderProductID());
            stmt.setString(2, op.getOrderID());
            stmt.setString(3, op.getProductID());
            stmt.setInt(4, op.getOrderedQuantity());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
    }

    public ArrayList<Order_Product> getAllRecord() {
        String queryStr = "SELECT * FROM " + tableName;
        Order_Product orderproduct = null;
        ArrayList<Order_Product> orderproductList = new ArrayList<Order_Product>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orderproduct = new Order_Product(rs.getString("OrderProductID"), rs.getString("OrderID"), rs.getString("ProductID"), rs.getInt("Ordered_Quantity"));

                orderproductList.add(orderproduct);
            }
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }

        return orderproductList;
    }

    public ArrayList<Order_Product> getOrderProductRecord1(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE OrderID=?";
        Order_Product orderproduct = null;
        ArrayList<Order_Product> orderproductList = new ArrayList<Order_Product>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orderproduct = new Order_Product(rs.getString("OrderProductID"), rs.getString("OrderID"), rs.getString("ProductID"), rs.getInt("Ordered_Quantity"));

                orderproductList.add(orderproduct);
            }
        } catch (SQLException ex) {
           System.out.println("****ERROR: " + ex.getMessage());
        }

        return orderproductList;
    }

    public ArrayList<Order_Product> getOrderProductRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE OrderID=?";
        Order_Product orderproduct = null;
        ArrayList<Order_Product> orderproductList = new ArrayList<Order_Product>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orderproduct = new Order_Product(rs.getString("OrderProductID"), rs.getString("OrderID"), rs.getString("ProductID"), rs.getInt("Ordered_Quantity"));
                orderproductList.add(orderproduct);
            }
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }

        return orderproductList;
    }

    public int generateID() {

        String queryStr = "SELECT count(*) as readcount FROM " + tableName;

        int num = 0;
        try {
            stmt = conn.prepareStatement(queryStr);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            num = rs.getInt("readcount");
            num++;
        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }
        return num;
    }

    public ArrayList<Order_Product> getAll() throws SQLException {
        String queryStr = "SELECT * FROM " + tableName;
        ResultSet rs;

        Order_Product order = null;
        stmt = conn.prepareStatement(queryStr);
        rs = stmt.executeQuery();
        ArrayList<Order_Product> orderList = new ArrayList<Order_Product>();
        while (rs.next()) {
            order = new Order_Product(rs.getString("OrderProductID"), rs.getString("OrderID"), rs.getString("ProductID"), rs.getInt("Ordered_Quantity"));
            orderList.add(order);
        }

        return orderList;
    }

    public Order_Product getRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE OrderProductID = ?";
        Order_Product orderprod = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

            }
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
        return orderprod;
    }

    public void updateRecord(Order_Product op) {
        try {
            String updateStr = "UPDATE " + tableName
                    + " SET OrderId = ?, ProductID = ?, Order_Quantity = ?  WHERE OrderProductID = ? ";

            stmt = conn.prepareStatement(updateStr);

            stmt.setString(1, op.getOrderID());
            stmt.setString(2, op.getProductID());
            stmt.setInt(3, op.getOrderedQuantity());
            stmt.setString(4, op.getOrderProductID());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("******ERROR: " + ex.getMessage());
        }
    }

    public void deleteRecord(String id) {
        try {
            String deleteStr = "DELETE FROM " + tableName + " WHERE OrderProductID = ?";
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
