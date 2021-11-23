/*
 * Author: mroge
 * Purpose: interface to be implemented by the UserIOConsoleImpl class file
 * This interface defines the methods that must be implemented by any class 
 * that wants to directly interact with the user interface technology
 *
 * the FlooringMasteryView only uses this interface to interact with the user — 
 * it is completely unaware of the implementation details*
 */
package com.mrr.flooringmastery.view;

    
import java.math.BigDecimal;
import java.time.LocalDate;

// interface declaration
public interface UserIO {
    // print function with one param String
    void print(String msg);

    String formatCurrency(BigDecimal amount);
    
    // readInt fuinction with one param String
    int readInt(String prompt);

    // readInt function with 3 params: String, int, int
    int readInt(String prompt, int min, int max);

    // readString function with one param String
    String readString(String prompt);
    
    String readString(String prompt, int max);
    
    BigDecimal readBigDecimal(String prompt, int scale);

    BigDecimal readBigDecimal(String prompt, int scale, BigDecimal min);
    
    LocalDate readDate(String prompt);
    
    LocalDate readDate(String prompt, LocalDate min, LocalDate max);
}
