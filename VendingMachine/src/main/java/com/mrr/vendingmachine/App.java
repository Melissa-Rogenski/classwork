
package com.mrr.vendingmachine;

import com.mrr.vendingmachine.controller.VendingMachineController;
import com.mrr.vendingmachine.dao.VendingMachineAuditDao;
import com.mrr.vendingmachine.dao.VendingMachineAuditDaoImpl;
import com.mrr.vendingmachine.dao.VendingMachineDao;
import com.mrr.vendingmachine.dao.VendingMachineDaoImpl;
import com.mrr.vendingmachine.service.VendingMachineServiceLayer;
import com.mrr.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.mrr.vendingmachine.ui.UserIO;
import com.mrr.vendingmachine.ui.UserIOConsoleImpl;
import com.mrr.vendingmachine.ui.VendingMachineView;

public class App {
    
    public static void main(String[] args) {
        
        UserIO myIo = new UserIOConsoleImpl();
        
        VendingMachineView myView = new VendingMachineView(myIo);
        
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoImpl();
        
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        
        VendingMachineController myController = new VendingMachineController(myView, myService);
        
        myController.run();
    }
}