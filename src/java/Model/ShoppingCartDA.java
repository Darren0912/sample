/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Member;
import Model.ShoppingCart;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Ding L
 */
public class ShoppingCartDA {

    private String host = "jdbc:derby://localhost:1527/TheBeast Database";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "ShoppingCart";
    private Connection conn;
    private PreparedStatement stmt;
    private MemberDA memberDA;

    public ShoppingCartDA() {
        createConnection();
        memberDA = new MemberDA();
    }

    public void addRecord(ShoppingCart shopCart) {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, shopCart.getCartID());
            stmt.setString(2, shopCart.getMemberID());
            stmt.setDouble(3, shopCart.getTotal());

            stmt.executeUpdate();
        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }
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
            id = "S" + num;

        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }
        return id;
    }

    public ShoppingCart getShopCartInformation(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE MemberID = ?";
        ShoppingCart shopCart = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                shopCart = new ShoppingCart(rs.getString("CartID"), rs.getString("MemberID"), rs.getDouble("Total"));
            }
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
        return shopCart;
    }

    public ShoppingCart getRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE CartID = ?";
        ShoppingCart cart = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cart = new ShoppingCart(rs.getString("CartID"), rs.getString("MemberID"), rs.getDouble("Total"));
            }
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
        return cart;
    }

    public ArrayList<ShoppingCart> getAllRecord(String memberID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE MemberID=?";
        ShoppingCart shopCart = null;
        ArrayList<ShoppingCart> cartList = new ArrayList<ShoppingCart>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                shopCart = new ShoppingCart(rs.getString("CartID"), memberID, rs.getDouble("SubTotal"));

                cartList.add(shopCart);
            }
        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }

        return cartList;
    }

    public void updateRecord(ShoppingCart cart, Member member) {
        try {
            String updateStr = "UPDATE " + tableName
                    + " SET Total = ? WHERE CartID = ? AND MemberID = ?";
            stmt = conn.prepareStatement(updateStr);
            stmt.setDouble(1, cart.getTotal());
            stmt.setString(2, cart.getCartID());
            stmt.setString(3, member.getMemberID());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("******ERROR: " + ex.getMessage());
        }
    }

    public void deleteRecord(String id) {
        try {
            String deleteStr = "DELETE FROM " + tableName + " WHERE MemberID = ?";
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
