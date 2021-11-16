/*
 * Author: mroge
 * Purpose: The DTO that holds all the Change info
 */
package com.mrr.vendingmachine.dto;

import java.math.BigDecimal;

// defining what is in a Change object
public class Change {
    
    // declaring private variables for the aspects of Change
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;
    
    // constructor using BigDecimal functionality to sort the big decimal into coin values
    public Change(BigDecimal amount) {
        this.quarters = amount.divide(new BigDecimal("25")).intValue();
        amount = amount.remainder(new BigDecimal("25"));
        this.dimes = amount.divide(new BigDecimal("10")).intValue();
        amount = amount.remainder(new BigDecimal("10"));
        this.nickels = amount.divide(new BigDecimal("5")).intValue();
        this.pennies = amount.remainder(new BigDecimal("5")).intValue();
    }
    
    // getter
    public int getQuarters() {
        return quarters;
    }
    
    // getter
    public int getDimes() {
        return dimes;
    }
    
    // getter
    public int getNickels() {
        return nickels;
    }
    
    //getter
    public int getPennies() {
        return pennies;
    }
}
