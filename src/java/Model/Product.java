/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Blob;
import java.util.Base64;

/**
 *
 * @author Ding L
 */
public class Product {
    private String ProductID;
    private String ProductName;
    private String Description;
    private String Byte64Image;
    private double Price;
    private String category;
    private String StaffID;
     private int total;

    
 
    
    public Product() {
    }

    public Product(String ProductID, String StaffID, String ProductName, String Description, double Price, String category, String byteimage) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Description = Description;
        this.Price = Price;
        this.category = category;
        this.Byte64Image=byteimage;
        this.StaffID=StaffID;
    }

    public Product(String ProductID,String ProductName,int total){
        this.ProductID=ProductID;
        this.ProductName=ProductName;
        this.total=total;
    }
    
    public String getByte64Image() {
        return Byte64Image;
    }

    public void setByte64Image(String Byte64Image) {
        this.Byte64Image = Byte64Image;
    }

    
    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String StaffID) {
        this.StaffID = StaffID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    @Override
    public String toString() {
        return "Product{" + "ProductID=" + ProductID + ", ProductName=" + ProductName + ", Description=" + Description + ", Byte64Image=" + Byte64Image + ", Price=" + Price + ", category=" + category + ", StaffID=" + StaffID + '}';
    }

    

    
  

   
   
    
    
}
