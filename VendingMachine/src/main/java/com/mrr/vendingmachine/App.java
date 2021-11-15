
package com.mrr.vendingmachine;

import com.mrr.vendingmachine.controller.VendingMachineController;
import com.mrr.vendingmachine.dao.VendingMachineDao;
import com.mrr.vendingmachine.dao.VendingMachineDaoImpl;
import com.mrr.vendingmachine.ui.UserIO;
import com.mrr.vendingmachine.ui.UserIOConsoleImpl;
import com.mrr.vendingmachine.ui.VendingMachineView;

public class App {
    
    public static void main(String[] args) {
        
        UserIO myIo = new UserIOConsoleImpl();
        
        VendingMachineView myView = new VendingMachineView(myIo);
        
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        
        VendingMachineController controller = new VendingMachineController(myDao, myView);
        
        controller.run();
    }
}
