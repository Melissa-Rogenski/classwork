/*
 * Author: mroge
 * Purpose: interface to be implemented by the FlooringMasteryAuditDaoImpl class file
 * This interface defines the methods that must be implemented by any class that
 * wants to play the role of AuditDAO in the application.
 *         
 * The FlooringMasteryServiceLayerImpl only uses this interface to reference the DAO — it 
 * is completely unaware of the implementation details.
 */
package com.mrr.flooringmastery.dao;
public interface FlooringMasteryAuditDao {
    
    /**
     * writes the audit to the file
     * @param entry the entry to be put into the file
     * @throws com.mrr.flooringmastery.service.FlooringMasteryPersistenceException
     */
    public void writeAuditEntry(String entry) throws DataPersistenceException;
}
