/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.BeastOrder;
import Model.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Ding L
 */
public class PaymentDA {

    private String host = "jdbc:derby://localhost:1527/TheBeast Database";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Payment";
    private Connection conn;
    private PreparedStatement stmt;

    public PaymentDA() {
        createConnection();
    }

    public void addRecord(Payment payment) {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, payment.getPaymentID());
            stmt.setDouble(2, payment.getDiscount());
            stmt.setDouble(3, payment.getTotalPrice());
            stmt.setString(4, payment.getPaymentMethod());
            stmt.setString(5, payment.getPaymentDate());
            stmt.setString(6, payment.getOrderID());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
    }

    public Payment getRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE PaymentID = ?";
        Payment payment = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                payment = new Payment(rs.getString("PaymentID"), rs.getDouble("Discount"), rs.getDouble("TotalPrice"), rs.getString("PaymentMethod"), rs.getString("PaymentDate"), id);
            }
        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }
        return payment;
    }

    public Payment getPaymentRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE OrderID = ?";
        Payment payment = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                payment = new Payment(rs.getString("PaymentID"), rs.getDouble("Discount"), rs.getDouble("TotalPrice"), rs.getString("PaymentMethod"), rs.getString("PaymentDate"), id);

            }
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
        return payment;
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
            id = "P" + num;

        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }
        return id;
    }

    public ArrayList<Payment> getPaymentListRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE OrderID=?";
        Payment payment = null;
        ArrayList<Payment> paymentList = new ArrayList<Payment>();
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                payment = new Payment(rs.getString("PaymentID"), rs.getDouble("Discount"), rs.getDouble("TotalPrice"), rs.getString("PaymentMethod"), rs.getString("PaymentDate"), id);

                paymentList.add(payment);
            }
        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }

        return paymentList;
    }

    public List<String> getSalesByDay(int year, int month, int day) throws SQLException {

        // Prepare the SQL statement to retrieve the sales records for a specific day
        String sql = "SELECT * FROM " + tableName + " WHERE PaymentID IN "
                + "(SELECT PaymentID FROM Payment WHERE YEAR(PaymentDate) = ? AND MONTH(PaymentDate) = ? AND DAY(PaymentDate) = ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, year);
        stmt.setInt(2, month);
        stmt.setInt(3, day);
        List order = new ArrayList<>();

        // Execute the SQL statement and retrieve the results
        ResultSet rs = stmt.executeQuery();

        // Iterate over the results and create OrderList objects
        while (rs.next()) {

            String orderID = rs.getString("OrderID");
            order.add(orderID);

        }

        // Clean up the resources
        rs.close();
        stmt.close();

        return order;
    }

    public List<String> getSalesByMonth(int year, int month) throws SQLException {

        // Prepare the SQL statement to retrieve the sales records for a specific month
        String sql = "SELECT * FROM " + tableName + " WHERE PaymentID IN "
                + "(SELECT PaymentID FROM Payment WHERE YEAR(PaymentDate) = ? AND MONTH(PaymentDate) = ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, year);
        stmt.setInt(2, month);
        List order = new ArrayList<>();

        // Execute the SQL statement and retrieve the results
        ResultSet rs = stmt.executeQuery();

        // Iterate over the results and create OrderList objects
        while (rs.next()) {

            String orderID = rs.getString("OrderID");
            order.add(orderID);

        }

        // Clean up the resources
        rs.close();
        stmt.close();

        return order;
    }

    public List<String> getSalesByYear(int year) throws SQLException {

        // Prepare the SQL statement to retrieve the sales records for a specific year
        String sql = "SELECT * FROM " + tableName + " WHERE PaymentID IN "
                + "(SELECT PaymentID FROM Payment WHERE YEAR(PaymentDate) = ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, year);
        List order = new ArrayList<>();

        // Execute the SQL statement and retrieve the results
        ResultSet rs = stmt.executeQuery();

        // Iterate over the results and create OrderList objects
        while (rs.next()) {

            String orderID = rs.getString("OrderID");
            order.add(orderID);

        }

        // Clean up the resources
        rs.close();
        stmt.close();

        return order;
    }

    public void updateRecord(Payment payment, BeastOrder order) {
        try {
            String updateStr = "UPDATE " + tableName
                    + " SET Discount = ?, TotalPrice = ?, PaymentMethod = ?, PaymentDate = ? WHERE PaymentID = ? AND OrderID = ?";

            stmt = conn.prepareStatement(updateStr);

            stmt.setString(1, String.valueOf(payment.getDiscount()));
            stmt.setString(2, String.valueOf(payment.getTotalPrice()));
            stmt.setString(3, payment.getPaymentMethod());
            stmt.setString(4, payment.getPaymentDate());
            stmt.setString(5, payment.getPaymentID());
            stmt.setString(6, order.getOrderID());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("******ERROR: " + ex.getMessage());
        }
    }

    public void deleteRecord(String id) {
        try {
            String deleteStr = "DELETE FROM " + tableName + " WHERE PaymentID = ?";
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
