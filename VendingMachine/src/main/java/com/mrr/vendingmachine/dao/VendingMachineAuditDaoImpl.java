/*
 * Author: mroge
 * Purpose: This file implements the VendingMachineAuditDao interface
 */
package com.mrr.vendingmachine.dao;

import com.mrr.vendingmachine.service.VendingMachinePersistenceException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

//implements keyword indicates this class implements the specified interface
public class VendingMachineAuditDaoImpl implements VendingMachineAuditDao {
    
    // creating a String variable that is public static final, 
    // this variable holds the file name that is to serve as a database for the Products
    public static final String AUDIT_FILE = "audit.txt";
    
    // public function to write a audit entry to a file
    // param string
    // throws VendingMachinePersistenceException
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        // initalizing printwriter
        PrintWriter out;
           
        //try catch block
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not persist audit information.", e);
        }
        // creating local date time variable
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        // flushing
        out.flush();
    }
}
