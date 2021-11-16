/*
 * Author: mroge
 * Purpose: interface to be implemented by the VendingMachineAuditDaoImpl class file
 * This interface defines the methods that must be implemented by any class that
 * wants to play the role of AuditDAO in the application.
 *         
 * The VendingMachineServiceLayerImpl only uses this interface to reference the DAO — it 
 * is completely unaware of the implementation details.
 */
package com.mrr.vendingmachine.dao;

import com.mrr.vendingmachine.service.VendingMachinePersistenceException;

// interface declaration
public interface VendingMachineAuditDao {
    
    /**
     * writes the audit to the file
     * @param entry the entry to be put into the file
     * @throws VendingMachinePersistenceException
     */
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
