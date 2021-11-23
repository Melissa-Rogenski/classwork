/*
 * Author: mroge
 * Purpose: The DTO that holds all the Tax info
 */
package com.mrr.flooringmastery.model;

import com.mrr.flooringmastery.dao.DataPersistenceException;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;


// defining what is in a Tax object
public class Tax {
    
    // declaring private variables for the aspects of a Tax
    private String stateAbbreviation;
    private String stateName;
    private BigDecimal taxRate;
    
    // declaring delimiter for parsing
    private final String DELIMITER = ",";
    
    //constructor
    //params String, String, BigDecimal
    public Tax(String stateAbbreviation, String stateName, BigDecimal taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }
    
    // constructor
    // param String
    // throws DataPersistenceException
    public Tax(String taxAsText) throws DataPersistenceException {
        // try catch block
        try {
            // PatternSyntaxException
            String[] fields = taxAsText.split(DELIMITER); 
            this.stateAbbreviation = fields[0];
            this.stateName = fields[1];
            this.taxRate = new BigDecimal(fields[2]);
        } catch(PatternSyntaxException e) {
            throw new DataPersistenceException(e.getMessage());
        } catch(NullPointerException | NumberFormatException e) {
            throw new DataPersistenceException(e.getMessage());
        }
    }
    
    //getter
    public String getStateAbbreviation() {
        return stateAbbreviation;
    } 
    //setter
    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }
    
    //getter
    public String getStateName() {
        return stateName;
    }
    //setter
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
    
    //getter
    public BigDecimal getTaxRate() {
        return taxRate;
    }
    //setter
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
    // hash function
    //returns int
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.stateAbbreviation);
        hash = 79 * hash + Objects.hashCode(this.stateName);
        hash = 79 * hash + Objects.hashCode(this.taxRate);
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
        final Tax other = (Tax) obj;
        if(this.taxRate != other.taxRate) {
            return false;
        }
        if(!Objects.equals(this.stateName, other.stateName)) {
           return false; 
        }
        if(!Objects.equals(this.stateAbbreviation, other.stateAbbreviation)) {
            return false;
        }
        return true;
    }
    
    // toString function
    // returns string
    @Override
    public String toString() {
        return "Tax{" + "stateAbbreviation=" + stateAbbreviation + 
                ", stateName=" + stateName + 
                ", taxRate=" + taxRate + 
                '}';
    }
    
    // marshalling function
    // returns string
    public String marshalTaxAsText() {
        return stateAbbreviation + DELIMITER + 
                stateName + DELIMITER + 
                taxRate;
    }
    
}
