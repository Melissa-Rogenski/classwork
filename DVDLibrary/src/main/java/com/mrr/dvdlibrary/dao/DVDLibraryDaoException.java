/*
 * Author: mroge
 * Purpose: The error class for the application. It extends Exception
 */
package com.mrr.dvdlibrary.dao;

//keyword extends used for inheritance
public class DVDLibraryDaoException extends Exception{
    
    // public function that calls the parent classes function in this case Exception
    public DVDLibraryDaoException(String message) {
        super(message);
    }
    
    // public function that calls the parent classes function in this case Exception
    public DVDLibraryDaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}