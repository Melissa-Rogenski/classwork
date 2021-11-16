
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
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testproducts.txt";
        // use filewriter to quickly blank he file
        new FileWriter(testFile, true);
        testDao = new VendingMachineDaoImpl(testFile);
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testWriteLoadProductsFromFile() throws Exception {
        System.out.println("writeLoadProductsFromFile");
        BigDecimal bd = new BigDecimal("2.50");
        Product p1 = new Product("1", "Coke", bd, 10);
        bd = new BigDecimal("1.05");
        Product p2 = new Product("2", "Water", bd, 12);
        testDao.addProduct(p1.getProductId(), p1);
        testDao.addProduct(p2.getProductId(), p2);
        testDao.writeProductsToFile();
        
        //Result
        Map<String, Product> result = testDao.loadProductsFromFile();
        //ExpectedResults
        Map<String, Product> expResult = new HashMap<>();
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
        //exp result
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
    
    @Test
    public void testGetAllProducts() {
        System.out.println("getAllProducts");
        BigDecimal bd = new BigDecimal("2.50");
        Product p1 = new Product("1", "Coke", bd, 10);
        bd = new BigDecimal("1.05");
        Product p2 = new Product("2", "Water", bd, 12);
        testDao.addProduct(p1.getProductId(), p1);
        testDao.addProduct(p2.getProductId(), p2);
        //result
        List<Product> result = testDao.getAllProducts();
        //exp result
        List<Product> expResult = new ArrayList<>();
        expResult.add(p1);
        expResult.add(p2);
        //assert
        assertNotNull(result, "The list of products must not null");
        assertEquals(2, result.size(), "List of products should have 2 products.");
        assertTrue(result.contains(p1), "The list of products should include p1.");
        assertTrue(result.contains(p2), "The list of products should include p2.");
        assertEquals(expResult, result, "2 lists of products should be the same.");
    }
    
    @Test
    public void testRemoveProduct() {
        System.out.println("removeProduct");
        BigDecimal bd = new BigDecimal("2.50");
        Product p1 = new Product("1", "Coke", bd, 10);
        bd = new BigDecimal("1.05");
        Product p2 = new Product("2", "Water", bd, 12);
        testDao.addProduct(p1.getProductId(), p1);
        testDao.addProduct(p2.getProductId(), p2);
        testDao.removeProduct(p1.getProductId());
        //result
        List<Product> result = testDao.getAllProducts();
        //exp result
        List<Product> expResult = new ArrayList<>();
        expResult.add(p2);
        
        //assert
        assertNotNull(result, "The list of products must not null");
        assertEquals(1, result.size(), "List of products should have 1 product.");
        assertFalse(result.contains(p1), "The list of products should not include p1.");
        assertTrue(result.contains(p2), "The list of products should include p2.");
        assertEquals(expResult, result, "2 lists of products should be the same.");
    }
    
    @Test
    public void testUpdateProduct() {
        System.out.println("updateProduct");
        BigDecimal bd = new BigDecimal("2.50");
        Product p1 = new Product("1", "Coke", bd, 10);
        testDao.addProduct(p1.getProductId(), p1);
        p1.setItemsInStock(9);
        testDao.updateProduct(p1.getProductId(), p1);
        //result
        Product result = testDao.getProduct(p1.getProductId());
        //exp result
        Product expResult = new Product("1", "Coke", bd, 9);
        
        //assert
        assertEquals(expResult.getProductId(), result.getProductId(), "Checking product id.");
        assertEquals(expResult.getProductName(), result.getProductName(), "Checking product name.");
        assertEquals(expResult.getPrice(), result.getPrice(), "Checking product price.");
        assertEquals(expResult.getItemsInStock(), result.getItemsInStock(), "Checking products items in stock.");
    }
}
