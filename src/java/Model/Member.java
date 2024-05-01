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
public class Member {

    private String MemberID;
    private String username;
    private String email;
    private String password;
    private String address;
    
    public Member() {
    }

    public Member(String MemberID, String username, String email, String password, String address) {
        this.MemberID = MemberID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String MemberID) {
        this.MemberID = MemberID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{" + "MemberID=" + MemberID + ", username=" + username + ", email=" + email + ", password=" + password + ", address=" + address + '}';
    }
    
    
    
}
