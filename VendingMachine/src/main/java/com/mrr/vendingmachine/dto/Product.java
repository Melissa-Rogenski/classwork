/*
 * Author: mroge
 * Purpose: The DTO that holds all the Product info
 */
package com.mrr.vendingmachine.dto;

import com.mrr.vendingmachine.service.VendingMachinePersistenceException;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

// defining what is in a Product object
public class Product {
    
    // declaring private variables forb the aspects of a Product
    private String productId;
    private String productName;
    private BigDecimal price;
    private int itemsInStock;
    
    // declaring delimiter for parsing
    private final String DELIMITER = "::";
    
    // constructor
    // param string, string, bigdecimal, int
    public Product(String productId, String productName, BigDecimal price, int itemsInStock) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.itemsInStock = itemsInStock;
    }
    
    // constructor
    // param string
    // throws VendingMachinePersistenceException
    public Product(String productAsText) throws VendingMachinePersistenceException {
        // try catch block
        try {
            // PatternSyntaxException
            String[] fields = productAsText.split(DELIMITER); 
            this.productId = fields[0];
            this.productName = fields[1];
            this.price = new BigDecimal(fields[2]);
            this.itemsInStock = Integer.parseInt(fields[3]);
        } catch(PatternSyntaxException e) {
            throw new VendingMachinePersistenceException(e.getMessage());
        } catch(NullPointerException | NumberFormatException e) {
            throw new VendingMachinePersistenceException(e.getMessage());
        }
    }
    
    // getter
    public int getItemsInStock() {
        return itemsInStock;
    }
    
    // setter
    public void setItemsInStock(int itemsInStock) {
        this.itemsInStock = itemsInStock;
    }
    
    // getter
    public String getProductId() {
        return productId;
    }
    
    // setter
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    // getter
    public String getProductName() {
        return productName;
    }
    
    // setter
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    // getter
    public BigDecimal getPrice() {
        return price;
    }
    
    // setter
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    // hash function
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.productId);
        hash = 79 * hash + Objects.hashCode(this.productName);
        hash = 79 * hash + Objects.hashCode(this.price);
        hash = 79 * hash + this.itemsInStock;
        return hash;
    }
    
    // equals function
    // param object
    @Override
    public boolean equals(Object obj){
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if(this.itemsInStock != other.itemsInStock) {
            return false;
        }
        if(!Objects.equals(this.productId, other.productId)) {
           return false; 
        }
        if(!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        if(!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }
    
    // toString function
    // returns string
    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", price=" + price + ", itemsInStock=" + itemsInStock + '}';
    }
    
    // marshalling function
    // returns string
    public String marshalProductAsText() {
        return productId + DELIMITER + productName + DELIMITER + price + DELIMITER + itemsInStock;
    }
    
}
