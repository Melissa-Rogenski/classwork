/*
 * Author: mroge
 * Purpose: this file is a test file to test all of the functionality in ServiceLayerImpl class
 */
package com.mrr.vendingmachine.service;

import com.mrr.vendingmachine.dao.VendingMachineAuditDao;
import com.mrr.vendingmachine.dao.VendingMachineDao;
import com.mrr.vendingmachine.dto.Change;
import com.mrr.vendingmachine.dto.Product;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;
import java.math.*;

public class VendingMachineServiceLayerImplTest {
    
    private VendingMachineServiceLayer testService;
    
    public VendingMachineServiceLayerImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        
        testService = new VendingMachineServiceLayerImpl(dao, auditDao);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testLoadProdcutsInStock() {
        try {
            System.out.println("loadProductsInStock");
            BigDecimal bd = new BigDecimal("2.50");
            Product p1 = new Product("1", "Coke", bd, 10);
            //expected result
            Map<String, Product> expResult = new TreeMap<>();
            expResult.put("1", p1);
            // Result
            Map<String, Product> result = testService.loadProductsInStock();
            //assert
            assertEquals(expResult, result, "Test Products loaded from file.");
        } catch(VendingMachineNoItemInventoryException | VendingMachinePersistenceException e) {
            // assert valid, no exceptions
            fail("Product was valid. No exception should have been thrown.");
        }
    }
    
    @Test
    public void testGetChosenProduct() throws VendingMachineNoItemInventoryException {
        System.out.println("getChosenProduct");
        BigDecimal bd = new BigDecimal("2.50");
        //expected result
        Product expResult = new Product("1", "Coke", bd, 10);
        // result
        Product result = testService.getChosenProduct("1");
        //assert
        assertEquals(expResult, result, "Checkboth products equal.");
        assertEquals(expResult.getProductName(), result.getProductName(), "check both product names are the same.");
    }
    
    @Test
    public void testCheckSufficientMoneyToBuyProduct_VendingMachineInsufficientFundsException()
        throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException {
        System.out.println("checkSufficientMoneyToBuyProduct");
        BigDecimal imputAmount = new BigDecimal(".50");
        Product product = testService.getChosenProduct("1");
        //assert
        Exception exception = assertThrows(VendingMachineInsufficientFundsException.class, () -> {
            testService.checkSufficientMoneyToBuyProduct(imputAmount, product);
        });
        
    }
    
    @Test
    public void testCheckSufficientMoneyToBuyProduct()
            throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException {
        try {
            System.out.println("checkSufficientFundsToBuyProduct");
            BigDecimal inputAmount = new BigDecimal("3.50");
            Product product = testService.getChosenProduct("1");
            testService.checkSufficientMoneyToBuyProduct(inputAmount, product);
        } catch(VendingMachineInsufficientFundsException e) {
            //assert
            fail("Sufficient funds to buy product. No exception should have been thrown.");
        }
    }
    
    @Test
    public void testCalculateChange() throws VendingMachineNoItemInventoryException {
        System.out.println("calculateChange");
        BigDecimal amount = new BigDecimal("5");
        Product product = testService.getChosenProduct("1");
        Change result = testService.calculateChange(amount, product);
        //assert
        assertEquals(10, result.getQuarters(), "Check number of quateres.");
        assertEquals(0, result.getDimes(), "Check number of dimes.");
        assertEquals(0, result.getNickels(), "Check number of nickels.");
        assertEquals(0, result.getPennies(), "Check number of pennies.");
    }
    
    @Test
    public void testUpdateProductSale() throws Exception {
            System.out.println("updateProductSale");
            Product product = testService.getChosenProduct("1");
            testService.updateProductSale(product);
            //expected result
            int expResult = 9;
            //result
            int result = product.getItemsInStock();
            //assert
            assertEquals(expResult, result, "Check if the product stock is updated.");
    }
    
}