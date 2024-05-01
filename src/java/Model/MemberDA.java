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
import Model.Member;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class MemberDA {

    private String host = "jdbc:derby://localhost:1527/TheBeast Database";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Member";
    private Connection conn;
    private PreparedStatement stmt;

    public MemberDA() {
        createConnection();
    }

    public void addRecord(Member member) {
        String insertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setString(1, member.getMemberID());
            stmt.setString(2, member.getUsername());
            stmt.setString(3, member.getEmail());
            stmt.setString(4, member.getPassword());
            stmt.setString(5, member.getAddress());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
    }

    public Boolean existMember(String username) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE username=? ";
        Boolean existmember = null;

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                existmember = true;
            } else {
                existmember = false;
            }
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
        return existmember;
    }

    public Boolean passwordCorrect(String username, String password) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE password=? ";
        Boolean passwordcorrect = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                passwordcorrect = true;
            } else {
                passwordcorrect = false;
            }
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
        return passwordcorrect;

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

    public Member getMemberInformation(String username) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE username=? ";
        Member member = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                member = new Member(rs.getString("MemberID"), username, rs.getString("email"), rs.getString("password"), rs.getString("address"));
            }
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
        return member;
    }

    public ArrayList<Member> getAllRecord() {
        String queryStr = "SELECT * FROM " + tableName;
        Member member = null;
        ArrayList<Member> memberList = new ArrayList<Member>();
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                member = new Member(rs.getString("MemberID"), rs.getString("username"), rs.getString("email"), rs.getString("password"),
                        rs.getString("address"));

                memberList.add(member);
            }
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }

        return memberList;
    }

    public ArrayList<Member> getAll() throws SQLException {
        String queryStr = "SELECT * FROM " + tableName;
        ResultSet rs;

        stmt = conn.prepareStatement(queryStr);
        rs = stmt.executeQuery();
        ArrayList<Member> memberList = new ArrayList<>();
        while (rs.next()) {
            Member member = new Member(rs.getString("MemberID"), rs.getString("UserName"), rs.getString("Email"), rs.getString("Password"), rs.getString("Address"));
            memberList.add(member);
        }

        return memberList;
    }

    public Member getRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE MemberID = ?";
        Member member = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                member = new Member(rs.getString("MemberID"), rs.getString("UserName"), rs.getString("Email"), rs.getString("Password"), rs.getString("Address"));
            }
        } catch (SQLException ex) {
            System.out.println("****ERROR: " + ex.getMessage());
        }
        return member;
    }

    public void updateRecord(Member member) {
        try {
            String updateStr = "UPDATE " + tableName
                    + " SET Username = ?, Email = ?, Password = ?, Address = ? WHERE MemberID = ?";
            stmt = conn.prepareStatement(updateStr);

            stmt.setString(1, member.getUsername());
            stmt.setString(2, member.getEmail());
            stmt.setString(3, member.getPassword());
            stmt.setString(4, member.getAddress());
            stmt.setString(5, member.getMemberID());
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

    public ResultSet selectRecord() {

        String strquery = "Select * From " + tableName;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(strquery);
            rs = stmt.executeQuery();

        } catch (SQLException ex) {

            System.out.println("****ERROR: " + ex.getMessage());

        }

        return rs;
    }
}
