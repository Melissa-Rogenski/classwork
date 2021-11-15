
package com.mrr.vendingmachine.dto;


public enum CoinValue {
    QUARTERS(25), DIMES(10), NICKELS(5), PENNIES(1);
    
    private int value;
            
    CoinValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}
