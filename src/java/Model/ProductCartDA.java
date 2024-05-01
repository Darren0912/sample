/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Product;
import Model.ShoppingCart;
import Model.ProductCart;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Ding L
 */
public class ProductCartDA {

    private String host = "jdbc:derby://localhost:1527/TheBeast Database";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "ProductCart";
    private Connection conn;
    private PreparedStatement stmt;
    private ShoppingCartDA cartDA;
    private ProductDA productDA;

    public ProductCartDA() {
        createConnection();
        cartDA = new ShoppingCartDA();
        productDA = new ProductDA();
    }

    public void addRecord(String pcartid, String id, String pdid, int quantity, int size) {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, pcartid);
            stmt.setString(2, id);
            stmt.setString(3, pdid);
            stmt.setInt(4, quantity);
            stmt.setInt(5, size);

            stmt.executeUpdate();

        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }
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

    public ArrayList<ProductCart> getAllRecord() {
        String queryStr = "SELECT * FROM " + tableName;
        ProductCart prodcart = null;
        ArrayList<ProductCart> productcartList = new ArrayList<ProductCart>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                prodcart = new ProductCart(rs.getString("ProductCartID"), rs.getString("cartID"), rs.getString("productID"), rs.getInt("Quantity"),
                        rs.getInt("Size"));
                

                productcartList.add(prodcart);
            }
        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }

        return productcartList;
    }

    public ArrayList<ProductCart> getProductCartRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE cartID=?";
        ProductCart prodCart = null;

        ArrayList<ProductCart> prodCartList = new ArrayList<ProductCart>();

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                prodCart = new ProductCart(rs.getString("ProductCartID"), rs.getString("cartID"), rs.getString("productID"), rs.getInt("Quantity"), rs.getInt("Size"));

                prodCartList.add(prodCart);
            }

        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }

        return prodCartList;
    }

    public ProductCart getRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE CartID = ?";
        ProductCart pcart = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

            }
        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }
        return pcart;
    }

    public void updateQuantity(String id,int quantity) {
        try {
            String updateStr = "UPDATE " + tableName
                    + " SET Quantity = ? WHERE ProductID = ? ";
            stmt = conn.prepareStatement(updateStr);
            stmt.setInt(1, quantity);
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("******ERROR: " + ex.getMessage());
        }
    }
    
    public void updateRecord(ProductCart pcart, ShoppingCart cart, Product product) {
        try {
            String updateStr = "UPDATE " + tableName
                    + " SET Quantity = ?, Size = ? WHERE ProductID = ? AND CartID= ?";
            stmt = conn.prepareStatement(updateStr);
            stmt.setString(1, String.valueOf(pcart.getQuantity()));
            stmt.setString(2, String.valueOf(pcart.getSize()));
            stmt.setString(3, product.getProductID());
            stmt.setString(4, cart.getCartID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("******ERROR: " + ex.getMessage());
        }
    }

    public void deleteRecord(String id) {
        try {
            String deleteStr = "DELETE FROM " + tableName + " WHERE CartID = ?";
            stmt = conn.prepareStatement(deleteStr);
            stmt.setString(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }
    }
    
    public void deleteProductCartRecord(String id) {
        try {
            String deleteStr = "DELETE FROM " + tableName + " WHERE ProductID = ?";
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
