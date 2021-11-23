/*
 * Author: mroge
 * Purpose: The DTO that holds all the Order info
 */
package com.mrr.flooringmastery.model;

import com.mrr.flooringmastery.dao.DataPersistenceException;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;
import java.time.LocalDate;

// defining what is in a Order object
public class Order {
    // declaring private variables for the aspects of a Order
    private LocalDate date;
    private int orderNumber;
    private String customerName;
    private String state;
    private String productType;
    private BigDecimal area;
    private BigDecimal taxRate;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    
    // declaring delimiter for parsing
    private final String DELIMITER = ",";
    
    // default constructor
    public Order() {
    }
    
    // constructor
    // param String
    // throws DataPersistenceException
    public Order(String orderAsText) throws DataPersistenceException {
        // try catch block
        try {
            // PatternSyntaxException
            String[] fields = orderAsText.split(DELIMITER); 
            this.orderNumber = Integer.parseInt(fields[0]);
            this.customerName = fields[1];
            this.state = fields[2];
            this.taxRate = new BigDecimal(fields[3]);
            this.productType = fields[4];
            this.area = new BigDecimal(fields[5]);
            this.costPerSquareFoot = new BigDecimal(fields[6]);
            this.laborCostPerSquareFoot = new BigDecimal(fields[7]);
            this.materialCost = new BigDecimal(fields[8]);
            this.laborCost = new BigDecimal(fields[9]);
            this.tax = new BigDecimal(fields[10]);
            this.total = new BigDecimal(fields[11]);
        } catch(PatternSyntaxException e) {
            throw new DataPersistenceException(e.getMessage());
        } catch(NullPointerException | NumberFormatException e) {
            throw new DataPersistenceException(e.getMessage());
        }
    }
    
    //getter
    public LocalDate getDate() {
        return date;
    }
    //setter
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    //getter
    public int getOrderNumber() {
        return orderNumber;
    }
    //setter
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    //getter
    public String getCustomerName() {
        return customerName;
    }
    //setter
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    //getter
    public String getState() {
        return state;
    }
    //setter
    public void setState(String state) {
        this.state = state;
    }
    
    //getter
    public BigDecimal getTaxRate() {
        return taxRate;
    }
    //setter
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
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
    public BigDecimal getArea() {
        return area;
    }
    //setter
    public void setArea(BigDecimal area) {
        this.area = area;
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
    
    //getter
    public BigDecimal getMaterialCost() {
        return materialCost;
    }
    //setter
    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }
    
    //getter
    public BigDecimal getLaborCost() {
        return laborCost;
    }
    //setter
    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }
    
    //getter
    public BigDecimal getTax() {
        return tax;
    }
    //setter
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    
    //getter
    public BigDecimal getTotal() {
        return total;
    }
    //setter
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    // hash function
    //returns int
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.orderNumber);
        hash = 79 * hash + Objects.hashCode(this.customerName);
        hash = 79 * hash + Objects.hashCode(this.state);
        hash = 79 * hash + Objects.hashCode(this.taxRate);
        hash = 79 * hash + Objects.hashCode(this.productType);
        hash = 79 * hash + Objects.hashCode(this.area);
        hash = 79 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 79 * hash + Objects.hashCode(this.laborCostPerSquareFoot);
        hash = 79 * hash + Objects.hashCode(this.materialCost);
        hash = 79 * hash + Objects.hashCode(this.laborCost);
        hash = 79 * hash + Objects.hashCode(this.tax);
        hash = 79 * hash + Objects.hashCode(this.total);
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
        final Order other = (Order) obj;
        if(this.total != other.total) {
            return false;
        }
        if(!Objects.equals(this.tax, other.tax)) {
           return false; 
        }
        if(!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if(!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if(!Objects.equals(this.laborCostPerSquareFoot, other.laborCostPerSquareFoot)) {
            return false;
        }
        if(!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
            return false;
        }
        if(!Objects.equals(this.area, other.area)) {
            return false;
        }
        if(!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if(!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if(!Objects.equals(this.state, other.state)) {
            return false;
        }
        if(!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if(!Objects.equals(this.orderNumber, other.orderNumber)) {
            return false;
        }
        return true;
    }
    
    // toString function
    // returns string
    @Override
    public String toString() {
        return "Order{" + "orderNumber=" + orderNumber + 
                ", customerName=" + customerName + 
                ", state=" + state + 
                ", taxRate=" + taxRate + 
                ", productType=" + productType + 
                ", area=" + area + 
                ", costPerSquareFoot=" + costPerSquareFoot + 
                ", laborCostPerSquareFoot=" + laborCostPerSquareFoot + 
                ", materialCost=" + materialCost + 
                ", laborCost=" + laborCost + 
                ", tax=" + tax + 
                ", total=" + total + 
                '}';
    }
    
    // marshalling function
    // returns string
    public String marshalOrderAsText() {
        return orderNumber + DELIMITER + 
                customerName + DELIMITER + 
                state + DELIMITER + 
                taxRate  + DELIMITER + 
                productType + DELIMITER + 
                area + DELIMITER + 
                costPerSquareFoot + DELIMITER + 
                laborCostPerSquareFoot + DELIMITER + 
                materialCost + DELIMITER + 
                laborCost + DELIMITER + 
                tax + DELIMITER + 
                total;
    }
    
}