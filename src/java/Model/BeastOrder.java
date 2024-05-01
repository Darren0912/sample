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
public class BeastOrder {

    private String OrderID;
    private String ShoppingCartID;
    private String OrderStatus;
    private double DeliveryFee;

    public BeastOrder() {
    }

    public BeastOrder(String OrderID, String ShoppingCartID, String OrderStatus, double DeliveryFee) {
        this.OrderID = OrderID;
        this.ShoppingCartID = ShoppingCartID;
        this.OrderStatus = OrderStatus;
        this.DeliveryFee = DeliveryFee;
    }

    public String getShoppingCartID() {
        return ShoppingCartID;
    }

    public void setShoppingCartID(String ShoppingCartID) {
        this.ShoppingCartID = ShoppingCartID;
    }

    

 
    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String OrderStatus) {
        this.OrderStatus = OrderStatus;
    }

    public double getDeliveryFee() {
        return DeliveryFee;
    }

    public void setDeliveryFee(double DeliveryFee) {
        this.DeliveryFee = DeliveryFee;
    }

    @Override
    public String toString() {
        return "BeastOrder{" + "OrderID=" + OrderID + ", ShoppingCartID=" + ShoppingCartID + ", OrderStatus=" + OrderStatus + ", DeliveryFee=" + DeliveryFee + '}';
    }

    

}
