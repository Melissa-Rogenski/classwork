/*
 * Author: mroge
 * Purpose: This file implements the FlooringMasteryAuditDao interface
 */
package com.mrr.flooringmastery.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

//implements keyword indicates this class implements the specified interface
public class FlooringMasteryAuditDaoImpl implements FlooringMasteryAuditDao {
       
    // creating a String variable that is public static final, 
    // this variable holds the file name that is to serve as a database for the Orders
    public static final String AUDIT_FILE = "audit.txt";
    
    // public function to write a audit entry to a file
    // param string
    // throws DataPersistenceException
    @Override
    public void writeAuditEntry(String entry) throws DataPersistenceException {
        // initalizing printwriter
        PrintWriter out;
           
        //try catch block
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new DataPersistenceException("Could not persist audit information.", e);
        }
        // creating local date time variable
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        // flushing
        out.flush();
    }
}
