/*
 * Author: mroge
 * Purpose: stub implementation
 */
package com.mrr.vendingmachine.service;

import com.mrr.vendingmachine.dao.VendingMachineAuditDao;

//implements keyword indicates this class implements the specified interface
public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDao {
    
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        //do nothing . . .
    }
}
