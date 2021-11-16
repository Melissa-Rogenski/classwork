/*
 * Author: mroge
 * Purpose: The error class for the application. It extends Exception
 */
package com.mrr.vendingmachine.service;

//keyword extends used for inheritance
public class VendingMachineDataValidationException extends Exception{
    
    // public function that calls the parent classes function in this case Exception
    public VendingMachineDataValidationException(String message) {
        super(message);
    }
    
    // public function that calls the parent classes function in this case Exception
    public VendingMachineDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
