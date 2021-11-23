/*
 * Author: mroge
 * Purpose: The error class for the application. It extends Exception
 */
package com.mrr.flooringmastery.dao;

//keyword extends used for inheritance
public class DataPersistenceException extends Exception{
    
    // public function that calls the parent classes function in this case Exception
    public DataPersistenceException(String message) {
        super(message);
    }
    
    // public function that calls the parent classes function in this case Exception
    public DataPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}