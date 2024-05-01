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
public class Payment {

  

    private String PaymentID;
    private double Discount;
    private double TotalPrice;
    private String PaymentMethod;
    private String PaymentDate;
    private String orderID;

    public Payment() {
    }

    public Payment(String PaymentID, double Discount, double TotalPrice, String PaymentMethod, String PaymentDate, String orderID) {
        this.PaymentID = PaymentID;
        this.Discount = Discount;
        this.TotalPrice = TotalPrice;
        this.PaymentMethod = PaymentMethod;
        this.PaymentDate= PaymentDate;
        this.orderID = orderID;
    }

    
    public String getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(String PaymentID) {
        this.PaymentID = PaymentID;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(String PaymentDate) {
        this.PaymentDate = PaymentDate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "Payment{" + "PaymentID=" + PaymentID + ", Discount=" + Discount + ", TotalPrice=" + TotalPrice + ", PaymentMethod=" + PaymentMethod + ", PaymentDate=" + PaymentDate + ", orderID=" + orderID + '}';
    }

   

   

}
