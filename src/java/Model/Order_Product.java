/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author User
 */
public class Order_Product {

    private String orderProductID;
    private String orderID;
    private String productID;
    private int orderedQuantity;

    public Order_Product() {
    }

    public Order_Product(String orderProductID, String orderID, String productID, int orderedQuantity) {
        this.orderProductID = orderProductID;
        this.orderID = orderID;
        this.productID = productID;
        this.orderedQuantity = orderedQuantity;
    }

    public String getOrderProductID() {
        return orderProductID;
    }

    public void setOrderProductID(String orderProductID) {
        this.orderProductID = orderProductID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    @Override
    public String toString() {
        return "Order_Product{" + "orderProductID=" + orderProductID + ", orderID=" + orderID + ", productID=" + productID + ", orderedQuantity=" + orderedQuantity + '}';
    }
}
