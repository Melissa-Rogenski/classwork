/*
 * Author: mroge
 * Purpose: interface to be implemented by the UserIOConsoleImpl class file
 * This interface defines the methods that must be implemented by any class 
 * that wants to directly interact with the user interface technology
 *
 * the VendingMachineView only uses this interface to interact with the user — 
 * it is completely unaware of the implementation details*
 */
package com.mrr.vendingmachine.ui;

// interface declaration
public interface UserIO {
    // print function with one param String
    void print(String msg);
    
    // readDouble function with one param String
    double readDouble(String prompt);

    // readDouble function with 3 params: String, double, double
    double readDouble(String prompt, double min, double max);

    // readFloat function with one param String
    float readFloat(String prompt);

    // readFloat function with 3 params: String, float, float
    float readFloat(String prompt, float min, float max);

    // readInt fuinction with one param String
    int readInt(String prompt);

    // readInt function with 3 params: String, int, int
    int readInt(String prompt, int min, int max);

    // readlong function with one param String
    long readLong(String prompt);

    // readLong function with three params: String, long, long
    long readLong(String prompt, long min, long max);

    // readString function with one param String
    String readString(String prompt);
    
}
