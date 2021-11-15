
package com.mrr.vendingmachine.dto;


public class Change {
    public int numQuarters;
    public int numDimes;
    public int numNickels;
    public int numPennies;
    
    public Change(int... enteredCoins) {
        this.numPennies = enteredCoins[0];
        this.numNickels = enteredCoins[1];
        this.numDimes = enteredCoins[2];
        this.numQuarters = enteredCoins[3];
    }
}
