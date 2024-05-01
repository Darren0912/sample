/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Staff;
import Model.Product;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Ding L
 */
public class ProductDA {

    private String host = "jdbc:derby://localhost:1527/TheBeast Database";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Product";
    private Connection conn;
    private PreparedStatement stmt;
    private StaffDA staffDA;

    public ProductDA() {
        createConnection();
        staffDA = new StaffDA();
    }

    public void addRecord(Product product, InputStream is) {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, product.getProductID());
            stmt.setString(2, product.getStaffID());
            stmt.setString(3, product.getProductName());
            stmt.setString(4, product.getDescription());
            stmt.setDouble(5, product.getPrice());
            stmt.setString(6, product.getCategory());
            stmt.setBlob(7, is);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
    }

    public int generateID() {
        String queryStr = "SELECT count(*) as readcount FROM " + tableName;

        int id = 0;
        int num = 0;
        try {
            stmt = conn.prepareStatement(queryStr);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            num = rs.getInt("readcount");

            num++;
            id = num;
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
        return id;
    }

    public Product getRecord(String id) throws IOException {
        String queryStr = "SELECT * FROM " + tableName + " WHERE ProductID = ?";
        Product product = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Blob blob = rs.getBlob("image");
                InputStream inputStream = blob.getBinaryStream();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();

                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                product = new Product(rs.getString("ProductID"), rs.getString("StaffID"), rs.getString("productName"), rs.getString("description"),
                        rs.getDouble("Price"), rs.getString("Category"), base64Image);

                inputStream.close();
                outputStream.close();
            }
        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }
        return product;
    }

    public ArrayList<Product> getAllRecord() throws IOException {

        String queryStr = "SELECT * FROM " + tableName;
        Product product = null;
        ArrayList<Product> productList = new ArrayList<Product>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Blob blob = rs.getBlob("image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();

                String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                product = new Product(rs.getString("ProductID"), rs.getString("StaffID"), rs.getString("productName"), rs.getString("description"),
                        rs.getDouble("Price"), rs.getString("Category"), base64Image);

                productList.add(product);

                inputStream.close();
                outputStream.close();
            }

        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }

        return productList;
    }

    public Product getProductRecord(String id) throws IOException {
        String queryStr = "SELECT * FROM " + tableName + " WHERE ProductID=?";
        Product product = null;

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Blob blob = rs.getBlob("image");
                InputStream inputStream = blob.getBinaryStream();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();

                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                product = new Product(rs.getString("ProductID"), rs.getString("StaffID"), rs.getString("productName"), rs.getString("description"),
                        rs.getDouble("Price"), rs.getString("Category"), base64Image);
                inputStream.close();
                outputStream.close();

            }
        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }

        return product;
    }

    public Product getIDRecord(String id) throws IOException {
        String queryStr = "SELECT * FROM " + tableName + " WHERE ProductName = ?";
        Product product = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Blob blob = rs.getBlob("image");
                InputStream inputStream = blob.getBinaryStream();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();

                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                product = new Product(rs.getString("ProductID"), rs.getString("StaffID"), rs.getString("productName"), rs.getString("description"),
                        rs.getDouble("Price"), rs.getString("Category"), base64Image);
                inputStream.close();
                outputStream.close();
            }
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
        return product;
    }

    public ArrayList<Product> getAll() throws SQLException, IOException {
        String queryStr = "SELECT * FROM " + tableName;

        Product product = null;
        stmt = conn.prepareStatement(queryStr);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Product> productList = new ArrayList<Product>();
        while (rs.next()) {
            Blob blob = rs.getBlob("image");
            InputStream inputStream = blob.getBinaryStream();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();

            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            product = new Product(rs.getString("ProductID"), rs.getString("StaffID"), rs.getString("ProductName"), rs.getString("Description"), rs.getDouble("Price"), rs.getString("Category"), base64Image);
            productList.add(product);
            inputStream.close();
            outputStream.close();
        }

        return productList;
    }

    public void updateRecord(Product product, InputStream is) {
        try {
            String updateStr = "UPDATE " + tableName
                    + " SET StaffID= ?, ProductName = ?,  Description= ?,  Price = ?, Category = ?, Image = ? WHERE ProductID = ?";

            stmt = conn.prepareStatement(updateStr);

            stmt.setString(1, product.getStaffID());
            stmt.setString(2, product.getProductName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setString(5, product.getCategory());
            stmt.setBlob(6, is);
            stmt.setString(7, product.getProductID());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("****ERROR: " + ex.getMessage());
        }
    }

    public void deleteRecord(String id) {
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
