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
public class ShoppingCart {
    private String CartID;
    private String MemberID;
    private double Total;

    public ShoppingCart() {
    }

    public ShoppingCart(String CartID, String MemberID, double Total) {
        this.CartID = CartID;
        this.MemberID = MemberID;
        this.Total = Total;
    }

    public String getCartID() {
        return CartID;
    }

    public void setCartID(String CartID) {
        this.CartID = CartID;
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String MemberID) {
        this.MemberID = MemberID;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" + "CartID=" + CartID + ", MemberID=" + MemberID + ", Total=" + Total + '}';
    }
    
}
