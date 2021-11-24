/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrr.flooringmastery.service;

import com.mrr.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mroge
 */
public class FlooringMasteryServiceLayerTest {
    
    private FlooringMasteryServiceLayer testService;
    
    public FlooringMasteryServiceLayerTest() {
        // wire the Service Layer with stub implementations of the Dao and
        // Audit Dao
        /*
        FlooringMasteryDao dao = new FlooringMasteryDaoStubImpl();
        FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoStubImpl();
        
        testService = new FlooringMasteryServiceLayerImpl(dao, auditDao);
        */ 
        
        
        ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
        testService = 
            ctx.getBean("serviceLayer", FlooringMasteryServiceLayer.class);   
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
    public void testGetOrders() throws Exception {
        try {
            List<Order> orders = testService.getOrders(LocalDate.of(1990, 01, 01));
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }
    }

    /**
     * Test of getOrder method, of class FloorServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrder() throws Exception {
        Order order = testService.getOrder(LocalDate.of(2020, 02, 18), 1);
        assertNotNull(order);

        try {
            order = testService.getOrder(LocalDate.of(2020, 02, 18), 20);
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

        try {
            testService.getOrder(LocalDate.of(1990, 01, 01), 1);
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

    }

    /**
     * Test of calculateOrder method, of class FloorServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateOrder() throws Exception {

        Order order1 = new Order();
        order1.setCustomerName("Temporary");
        order1.setState("TX");
        order1.setProductType("Laminate");
        order1.setArea(new BigDecimal("100"));

        Order order2 = new Order();
        order2.setCustomerName("Temporary");
        order2.setState("TX");
        order2.setProductType("Laminate");
        order2.setArea(new BigDecimal("100"));

        testService.calculateOrder(order1);
        testService.calculateOrder(order2);
        assertEquals(order1.getTotal(), order1.getTotal());

        order1.setCustomerName("");

        try {
            testService.calculateOrder(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setCustomerName("Temporary");
        order1.setState("");

        try {
            testService.calculateOrder(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setState("TX");
        order1.setProductType("");

        try {
            testService.calculateOrder(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setProductType("Wood");
        order1.setArea(new BigDecimal("0"));

        try {
            testService.calculateOrder(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }
    }

    /**
     * Test of addOrder method, of class FloorServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddOrder() throws Exception {

        Order order = new Order();
        order.setCustomerName("Temporary");
        order.setState("TX");
        order.setProductType("Wood");
        order.setArea(new BigDecimal("100"));
        testService.addOrder(order);

        assertEquals(order, testService.addOrder(order));

    }

    /**
     * Test of compareOrders method, of class FloorServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testCompareOrders() throws Exception {

        Order savedOrder = testService.getOrder(LocalDate.of(2020, 02, 18), 1);

        Order editedOrder = new Order();
        editedOrder.setCustomerName("Linda Rogenski");

        Order updatedOrder = testService.compareOrders(savedOrder, editedOrder);

        assertEquals(updatedOrder, savedOrder);

    }

    /**
     * Test of editOrder method, of class FloorServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testEditOrder() throws Exception {

        Order savedOrder = testService.getOrder(LocalDate.of(2020, 02, 18), 1);
        assertNotNull(savedOrder);

        try {
            savedOrder = testService.getOrder(LocalDate.of(2020, 02, 18), 20);
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

    }

    /**
     * Test of removeOrder method, of class FloorServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveOrder() throws Exception {

        Order removedOrder = testService.getOrder(LocalDate.of(2020, 02, 18), 1);
        assertNotNull(removedOrder);

        try {
            removedOrder = testService.getOrder(LocalDate.of(2020, 02, 18), 20);
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

    }
}
