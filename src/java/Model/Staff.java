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
public class Staff {

    private String StaffID;
    private String StaffName;
    private String Position;
    private String PhoneNo;
    private String password;

    public Staff() {
    }

    public Staff(String StaffID, String StaffName, String Position, String PhoneNo, String password) {
        this.StaffID = StaffID;
        this.StaffName = StaffName;
        this.Position = Position;
        this.PhoneNo = PhoneNo;
        this.password = password;
    }

    public Staff(String StaffName, String password) {
        this.StaffName = StaffName;
        this.password = password;
    }

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String StaffID) {
        this.StaffID = StaffID;
    }

    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String StaffName) {
        this.StaffName = StaffName;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String PhoneNo) {
        this.PhoneNo = PhoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Staff{" + "StaffID=" + StaffID + ", StaffName=" + StaffName + ", Position=" + Position + ", PhoneNo=" + PhoneNo + ", password=" + password + '}';
    }

}
