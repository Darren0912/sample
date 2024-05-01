/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Ding L
 */
import Model.Staff;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Ding L
 */
public class StaffDA {

    private String host = "jdbc:derby://localhost:1527/TheBeast Database";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Staff";
    private Connection conn;
    private PreparedStatement stmt;

    public StaffDA() {
        createConnection();
    }

    public void addRecord(Staff staff) {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, staff.getStaffID());
            stmt.setString(2, staff.getStaffName());
            stmt.setString(3, "staff");
            stmt.setString(4, staff.getPhoneNo());
            stmt.setString(5, staff.getPassword());

            stmt.executeUpdate();
        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }
    }
    
    public int generateID(){
        String queryStr="SELECT count(*) as readcount FROM " +tableName;
        
        int id=0;
        int num=0;
        try{
            stmt=conn.prepareStatement(queryStr);
            
            ResultSet rs=stmt.executeQuery();
            rs.next();
            num=rs.getInt("readcount");
            
            num++;
            id=num;
        }catch(SQLException ex){
             System.out.println("****ERROR: " + ex.getMessage());
        }
        return id;
    }

    public Staff getLogin(String username, String password) {
        Staff staff = null;

        String queryStr = "SELECT * FROM " + tableName + " WHERE Staffname = ? AND Password =?";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                staff = new Staff(rs.getString("StaffID"), username, rs.getString("Position"), rs.getString("PhoneNo"), password);
            }

        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }

        return staff;
    }

    public Staff getRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE StaffID = ?";
        Staff staff = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                staff = new Staff(rs.getString("StaffID"), rs.getString("StaffName"), rs.getString("Position"), rs.getString("PhoneNo"), rs.getString("Password"));
            }
        } catch (SQLException ex) {
             System.out.println("****ERROR: " + ex.getMessage());
        }
        return staff;
    }

    public ArrayList<Staff> getAll() throws SQLException {
        String queryStr = "SELECT * FROM " + tableName;
        ResultSet rs;
     
            stmt = conn.prepareStatement(queryStr);
            rs = stmt.executeQuery();
            ArrayList<Staff> staffList = new ArrayList<>();
            while (rs.next()) {
                Staff staff = new Staff(rs.getString("StaffID"), rs.getString("StaffName"), rs.getString("Position"), rs.getString("PhoneNo"), rs.getString("Password"));
                staffList.add(staff);
            }

        return staffList;
    }
    

    public void updateRecord(Staff staff) {
        try {
            String updateStr = "UPDATE " + tableName
                    + " SET StaffName = ?, Position = ?, PhoneNo = ?, Password = ? WHERE StaffID = ?";

            stmt = conn.prepareStatement(updateStr);
            stmt.setString(1, staff.getStaffName());
            stmt.setString(2, String.valueOf(staff.getPosition()));
            stmt.setString(3, staff.getPhoneNo());
            stmt.setString(4, staff.getPassword());
            stmt.setString(5, staff.getStaffID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("****ERROR: " + ex.getMessage());
        }
    }

    public void updatePassword(String id, String password) {
        try {
            String updateStr = "UPDATE " + tableName + " SET Password = ? WHERE staffID = ?";

            stmt = conn.prepareStatement(updateStr);
            stmt.setString(1, password);
            stmt.setString(2, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
    }

    public void deleteRecord(String id) {
        try {
            String deleteStr = "DELETE FROM " + tableName + " WHERE StaffID = ?";
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