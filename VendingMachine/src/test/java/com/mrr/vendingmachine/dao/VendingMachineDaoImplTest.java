
package com.mrr.vendingmachine.dao;

import com.mrr.vendingmachine.dto.Product;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class VendingMachineDaoImplTest {
    
    VendingMachineDao testDao;
    
    public VendingMachineDaoImplTest() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testproducts.txt";
        new FileWriter(testFile, true);
        testDao = new VendingMachineDaoImpl(testFile);
    }
    
    @Test
    public void testLoadProductsFromFile() throws Exception {
        System.out.println("loadProductsFromFile");
        BigDecimal bd = new BigDecimal("2.50");
        Product p1 = new Product("1", "Coke", bd, 10);
        bd = new BigDecimal("1.05");
        Product p2 = new Product("2", "Water", bd, 12);
        //Result
        Map<String, Product> result = testDao.loadProductsFromFile();
        //ExpectedResults
        Map<String, Product> expResult = new TreeMap<>();
        expResult.put("1", p1);
        expResult.put("2", p2);
        
        //Assert
        assertEquals(expResult, result, "Test Products loaded from the file.");
    }
    
    @Test
    public void testAddGetProduct() {
        System.out.println("addProduct");
        BigDecimal bd = new BigDecimal("2.50");
        Product p1 = new Product("1", "Coke", bd, 10);
        testDao.addProduct(p1.getProductId(), p1);
        //result
        Product result = testDao.getProduct(p1.getProductId());
        //exp result
        Product expResult = new Product("1", "Coke", bd, 10);
        //assert
        assertEquals(expResult.getProductId(), result.getProductId(), "Checking product id.");
        assertEquals(expResult.getProductName(), result.getProductName(), "Checking product name.");
        assertEquals(expResult.getPrice(), result.getPrice(), "Checking product price.");
        assertEquals(expResult.getItemsInStock(), result.getItemsInStock(), "Checking products items in stock.");
    }
    
    @Test
    public void testGetAllProductIds() {
        System.out.println("getAllProductIds");
        BigDecimal bd = new BigDecimal("2.50");
        Product p1 = new Product("1", "Coke", bd, 10);
        bd = new BigDecimal("1.05");
        Product p2 = new Product("2", "Water", bd, 12);
        testDao.addProduct(p1.getProductId(), p1);
        testDao.addProduct(p2.getProductId(), p2);
        //result
        List<String> result = testDao.getAllProductIds();
        //expresult
        List<String> expResult = new ArrayList<>();
        expResult.add("1");
        expResult.add("2");
        //assert
        assertNotNull(result, "The list of products ids must not null");
        assertEquals(2, result.size(), "List of product ids should have 2 product ids.");
        assertTrue(result.contains(p1.getProductId()), "The list of products should include coke id.");
        assertTrue(result.contains(p2.getProductId()), "The list of products should include water id.");
        assertEquals(expResult, result, "2 lists of product ids should be the same.");
    }
    
}
