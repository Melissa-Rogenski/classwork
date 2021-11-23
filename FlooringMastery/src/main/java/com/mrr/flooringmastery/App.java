/*
 * Author: mroge
 * Purpose: file that contains the main function
 */
package com.mrr.flooringmastery;

import com.mrr.flooringmastery.controller.FlooringMasteryController;
import com.mrr.flooringmastery.service.OrderValidationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    
    public static void main(String[] args) throws OrderValidationException {
        /*
        // creating a UserIO variable that calls the UserIOConsole constructor
        UserIO myIo = new UserIOConsoleImpl();
        // creating a FlooringMasteryView variable that calls the FlooringMasteryView constructor
        FlooringMasteryView myView = new FlooringMasteryView(myIo);
        // creating a FlooringMasteryDao variable that calls the FlooringMasteryDaoFileImpl constructor
        FlooringMasteryDao myDao = new FlooringMasteryDaoImpl();
        // creating a FlooringMasteryController variable that calls the FlooringMasteryController constructor
        FlooringMasteryAuditDao myAuditDao = new FlooringMasteryAuditDaoImpl();
        // creating a FlooringMasteryServiceLayer variable that calls the FlooringMasteryServiceLayerImpl constructor
        FlooringMasteryServiceLayer myService = new FlooringMasteryServiceLayerImpl(myDao, myAuditDao);
        // creating a FlooringMasteryController variable that calls the FlooringMasteryController constructor
        FlooringMasteryController myController = new FlooringMasteryController(myView, myService);
        // calling the run function of controller
        myController.run();
        */
        
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = 
           ctx.getBean("controller", FlooringMasteryController.class);
        controller.run();
    }
}
