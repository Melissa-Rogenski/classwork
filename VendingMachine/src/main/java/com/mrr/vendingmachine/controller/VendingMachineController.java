/*
 * Author: mroge
 * Purpose: The orchestrator of the application. It knows what needs to be done, when it 
 *          needs to be done, and what component can do the job.
 */
package com.mrr.vendingmachine.controller;

import com.mrr.vendingmachine.dao.VendingMachineDao;
import com.mrr.vendingmachine.ui.VendingMachineView;
import java.util.List;

public class VendingMachineController {
    
    // private variable of type VendingMachineView
    private VendingMachineView view;
    // private variable of type VendingMachineDao
    private VendingMachine dao;
    
    // public constructor for VendingMachineController that takes parameters VendingMachineDao and VendingMachineView
    public VendingMachineController(VendingMachineDao dao, VendingMachineView view) {
        // assigning value to dao
        this.dao = dao;
        // assigning value to view
        this.view = view;
    }
}
