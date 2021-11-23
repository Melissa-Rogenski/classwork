/*
 * Author: mroge
 * Purpose: The error class for the application. It extends Exception
 */
package com.mrr.flooringmastery.service;

//keyword extends used for inheritance
public class InvalidOrderNumberException extends Exception{
    
    // public function that calls the parent classes function in this case Exception
    public InvalidOrderNumberException(String message) {
        super(message);
    }
    
    // public function that calls the parent classes function in this case Exception
    public InvalidOrderNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}