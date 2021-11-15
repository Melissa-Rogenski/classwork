
package com.mrr.vendingmachine.dao;

import com.mrr.vendingmachine.service.VendingMachinePersistenceException;


public interface VendingMachineAuditDao {
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
