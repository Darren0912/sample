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
public class ProductCart {
    private String ProductCartID;
    private String cartID;
    private String productID;
    private int Quantity;
    private int Size;

    public ProductCart() {
    }

    public ProductCart(String ProductCartID, String cartID, String productID, int Quantity, int Size) {
        this.ProductCartID = ProductCartID;
        this.cartID = cartID;
        this.productID = productID;
        this.Quantity = Quantity;
        this.Size = Size;
    }
    

    public String getProductCartID() {
        return ProductCartID;
    }

    public void setProductCartID(String ProductCartID) {
        this.ProductCartID = ProductCartID;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

  
    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int Size) {
        this.Size = Size;
    }
    
    @Override
    public String toString() {
        return "ProductCart{" + "cart=" + cartID + ", product=" + productID + ", Quantity=" + Quantity + ", Size=" + Size + '}';
    }

    
    
    
}
