/*
 * Author: mroge
 * Purpose: The DTO that holds all the Product info
 */
package com.mrr.flooringmastery.model;

import com.mrr.flooringmastery.dao.DataPersistenceException;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;


// defining what is in a Product object
public class Product {
    
    // declaring private variables for the aspects of a Order
    private String productType;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;

    // declaring delimiter for parsing
    private final String DELIMITER = ",";
    
    //constructor
    //params String, BigDecimal, BigDecimal
    public Product(String productType, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot) {
        this.productType = productType;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }
    
    // constructor
    // param String
    // throws DataPersistenceException
    public Product(String productAsText) throws DataPersistenceException {
        // try catch block
        try {
            // PatternSyntaxException
            String[] fields = productAsText.split(DELIMITER); 
            this.productType = fields[0];
            this.costPerSquareFoot = new BigDecimal(fields[1]);
            this.laborCostPerSquareFoot = new BigDecimal(fields[2]);
        } catch(PatternSyntaxException e) {
            throw new DataPersistenceException(e.getMessage());
        } catch(NullPointerException | NumberFormatException e) {
            throw new DataPersistenceException(e.getMessage());
        }
    }
    
    //getter
    public String getProductType() {
        return productType;
    } 
    //setter
    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    //getter
    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }
    //setter
    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }
    
    //getter
    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }
    //setter
    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }
    
    // hash function
    //returns int
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.productType);
        hash = 79 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 79 * hash + Objects.hashCode(this.laborCostPerSquareFoot);
        return hash;
    }
    
    // equals function
    // param object
    // returns boolean
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
        if(this.laborCostPerSquareFoot != other.laborCostPerSquareFoot) {
            return false;
        }
        if(!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
           return false; 
        }
        if(!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        return true;
    }
    
    // toString function
    // returns string
    @Override
    public String toString() {
        return "Product{" + "productType=" + productType + 
                ", costPerSquareFoot=" + costPerSquareFoot + 
                ", laborCostPerSquareFoot=" + laborCostPerSquareFoot + 
                '}';
    }
    
    // marshalling function
    // returns string
    public String marshalProductAsText() {
        return productType + DELIMITER + 
                costPerSquareFoot + DELIMITER + 
                laborCostPerSquareFoot;
    }
    
}
