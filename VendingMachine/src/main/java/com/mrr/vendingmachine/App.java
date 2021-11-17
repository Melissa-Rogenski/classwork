/*
 * Author: mroge
 * Purpose: file that contains the main function
 */
package com.mrr.vendingmachine;

import com.mrr.vendingmachine.controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    
    public static void main(String[] args) {
        /*// creating a UserIO variable that calls the UserIOConsole constructor
        UserIO myIo = new UserIOConsoleImpl();
        // creating a VendingMachineView variable that calls the VendingMachineView constructor
        VendingMachineView myView = new VendingMachineView(myIo);
        // creating a VendingMachineDao variable that calls the VendingMachineDaoFileImpl constructor
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        // creating a VendingMachineController variable that calls the VendingMachineController constructor
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoImpl();
        // creating a VendingMachineServiceLayer variable that calls the VendingMachineServiceLayerImpl constructor
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        // creating a VendingMachineController variable that calls the VendingMachineController constructor
        VendingMachineController myController = new VendingMachineController(myView, myService);
        // calling the run function of controller
        myController.run();*/
    
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = 
           ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }
}