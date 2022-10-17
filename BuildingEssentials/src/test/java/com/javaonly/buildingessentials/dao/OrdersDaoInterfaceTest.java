/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.dao;

import com.javaonly.buildingessentials.TestApplicationConfiguration;
import com.javaonly.buildingessentials.dto.Orders;
import com.javaonly.buildingessentials.dto.Products;
import com.javaonly.buildingessentials.dto.Taxes;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Prathima
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)

public class OrdersDaoInterfaceTest {
    
     @Autowired
     ProductsDaoInterface productDao;
    
    @Autowired
    TaxesDaoInterface taxDao;
    
    @Autowired
    OrdersDaoInterface orderDao;
    
    
    
   
    public OrdersDaoInterfaceTest() {
    }
    
    
    @Before
    public void setUp() {
        List<Products> products = productDao.getAllProducts();
        for(Products allProducts: products){
            productDao.deleteProduct(allProducts.getProductId());
        }
        List<Taxes> taxes = taxDao.getAllTaxes();
        for(Taxes alltaxes: taxes){
            taxDao.deleteTax(alltaxes.getTaxId());
        }
        
        List<Orders> orders = orderDao.getAllOrders();
        for(Orders allOrders: orders){
            orderDao.deleteOrder(allOrders.getOrderId());
        }
    }
    
    /**
     * Test of addOrder method, of class OrdersDaoInterface.
     */
    @Test
    public void testAddOrder() {
        Products product = new Products();
        product.setProductType("wood");
        product.setCostPerSquareFoot(new BigDecimal("5.72"));
         product.setLaborCostPerSquareFoot(new BigDecimal("8.90"));
        product = productDao.addOneProduct(product);
        
        Taxes tax = new Taxes();
        tax.setTaxRate(new BigDecimal("3.50"));
        tax.setStateName("utah");
        tax.setStateAbbrevation("ut");
        tax = taxDao.addTax(tax);
        
        Orders order = new Orders();
        order.setCustomerName("jhon");
        order.setOrderDate(LocalDate.now());
        order.setArea(new BigDecimal("10.00"));
        order.setTaxId(tax.getTaxId());
        order.setProductId(product.getProductId());
//        order.setTax(tax);
//        order.setProduct(product);
        order.setMaterialCost(new BigDecimal("57.20"));
        order.setTotalLaborCost(new BigDecimal("80.00"));
        order.setTotalTaxCost(new BigDecimal("4.81"));
        order.setTotalOrderCost(new BigDecimal("142.020"));
        
         orderDao.addOrder(order);
        Orders oneOrder = orderDao.addOrder(order);
        assertSame(oneOrder,order);
        
    }

    /**
     * Test of getAllOrders method, of class OrdersDaoInterface.
     */
    @Test
    public void testGetAllOrders() {
        
        Products product = new Products();
        product.setProductType("wood");
        product.setCostPerSquareFoot(new BigDecimal("5.72"));
         product.setLaborCostPerSquareFoot(new BigDecimal("8.90"));
        product = productDao.addOneProduct(product);
        
        Taxes tax = new Taxes();
        tax.setTaxRate(new BigDecimal("3.50"));
        tax.setStateName("utah");
        tax.setStateAbbrevation("ut");
        tax = taxDao.addTax(tax);
        
        Orders order = new Orders();
        order.setCustomerName("jhon");
        order.setOrderDate(LocalDate.now());
        order.setArea(new BigDecimal("10.00"));
        order.setTaxId(tax.getTaxId());
        order.setProductId(product.getProductId());
//        order.setTax(tax);
//        order.setProduct(product);
        order.setMaterialCost(new BigDecimal("57.20"));
        order.setTotalLaborCost(new BigDecimal("80.00"));
        order.setTotalTaxCost(new BigDecimal("4.81"));
        order.setTotalOrderCost(new BigDecimal("142.020"));
        
       order=  orderDao.addOrder(order);
          List<Orders> allOrders = orderDao.getAllOrders();
       assertNotNull(allOrders);
    }

    /**
     * Test of getOneOrder method, of class OrdersDaoInterface.
     */
    @Test
    public void testGetOneOrder() {
    }

    /**
     * Test of updateOrder method, of class OrdersDaoInterface.
     */
    @Test
    public void testUpdateOrder() {
        
         Products product = new Products();
        product.setProductType("wood");
        product.setCostPerSquareFoot(new BigDecimal("5.72"));
         product.setLaborCostPerSquareFoot(new BigDecimal("8.90"));
        product = productDao.addOneProduct(product);
        
        Taxes tax = new Taxes();
        tax.setTaxRate(new BigDecimal("3.50"));
        tax.setStateName("utah");
        tax.setStateAbbrevation("ut");
        tax = taxDao.addTax(tax);
        
        Orders order = new Orders();
        order.setCustomerName("jhon");
        order.setOrderDate(LocalDate.now());
        order.setArea(new BigDecimal("10.00"));
        order.setTaxId(tax.getTaxId());
        order.setProductId(product.getProductId());
//        order.setTax(tax);
//        order.setProduct(product);
        order.setMaterialCost(new BigDecimal("57.20"));
        order.setTotalLaborCost(new BigDecimal("80.00"));
        order.setTotalTaxCost(new BigDecimal("4.81"));
        order.setTotalOrderCost(new BigDecimal("142.020"));
        
      order =   orderDao.addOrder(order);
       Orders orderFromDao = orderDao.getOneOrder(order.getOrderId());
       orderFromDao.setCustomerName("rocky");
      orderDao.updateOrder(orderFromDao);
       
       assertNotEquals(order,orderFromDao);
       
        
    }

    /**
     * Test of deleteOrder method, of class OrdersDaoInterface.
     */
    @Test
    public void testDeleteOrder() {
         Products product = new Products();
        product.setProductType("wood");
        product.setCostPerSquareFoot(new BigDecimal("5.72"));
         product.setLaborCostPerSquareFoot(new BigDecimal("8.90"));
        product = productDao.addOneProduct(product);
        
        Taxes tax = new Taxes();
        tax.setTaxRate(new BigDecimal("3.50"));
        tax.setStateName("utah");
        tax.setStateAbbrevation("ut");
        tax = taxDao.addTax(tax);
        
        Orders order = new Orders();
        order.setCustomerName("jhon");
        order.setOrderDate(LocalDate.now());
        order.setArea(new BigDecimal("10.00"));
        order.setTaxId(tax.getTaxId());
        order.setProductId(product.getProductId());
//        order.setTax(tax);
//        order.setProduct(product);
        order.setMaterialCost(new BigDecimal("57.20"));
        order.setTotalLaborCost(new BigDecimal("80.00"));
        order.setTotalTaxCost(new BigDecimal("4.81"));
        order.setTotalOrderCost(new BigDecimal("142.020"));
        
      order =   orderDao.addOrder(order);
       
      orderDao.deleteOrder(order.getOrderId());
      Orders oneOrder = orderDao.getOneOrder(order.getOrderId());
       assertNull(oneOrder);
        
    }

    /**
     * Test of getAllOrdersForOneDate method, of class OrdersDaoInterface.
     */
    @Test
    public void testGetAllOrdersForOneDate() {
         Products product = new Products();
        product.setProductType("wood");
        product.setCostPerSquareFoot(new BigDecimal("5.72"));
         product.setLaborCostPerSquareFoot(new BigDecimal("8.90"));
        product = productDao.addOneProduct(product);
        
        Taxes tax = new Taxes();
        tax.setTaxRate(new BigDecimal("3.50"));
        tax.setStateName("utah");
        tax.setStateAbbrevation("ut");
        tax = taxDao.addTax(tax);
        
        Orders order = new Orders();
        order.setCustomerName("jhon");
        order.setOrderDate(LocalDate.now());
        order.setArea(new BigDecimal("10.00"));
        order.setTaxId(tax.getTaxId());
        order.setProductId(product.getProductId());
//        order.setTax(tax);
//        order.setProduct(product);
        order.setMaterialCost(new BigDecimal("57.20"));
        order.setTotalLaborCost(new BigDecimal("80.00"));
        order.setTotalTaxCost(new BigDecimal("4.81"));
        order.setTotalOrderCost(new BigDecimal("142.020"));
        
      order =   orderDao.addOrder(order);
      List<Orders> allOrdersForDate = orderDao.getAllOrdersForOneDate(order.getOrderDate().toString());
      List<Orders> onlyOneOrderForTheDate = orderDao.getAllOrders();
      assertNotNull(allOrdersForDate);
        
    }

    /**
     * Test of getAllOrdersForOneCustomer method, of class OrdersDaoInterface.
     */
    @Test
    public void testGetAllOrdersForOneCustomer() {
        
         Products product = new Products();
        product.setProductType("wood");
        product.setCostPerSquareFoot(new BigDecimal("5.72"));
         product.setLaborCostPerSquareFoot(new BigDecimal("8.90"));
        product = productDao.addOneProduct(product);
        
        Taxes tax = new Taxes();
        tax.setTaxRate(new BigDecimal("3.50"));
        tax.setStateName("utah");
        tax.setStateAbbrevation("ut");
        tax = taxDao.addTax(tax);
        
        Orders order = new Orders();
        order.setCustomerName("jhon");
        order.setOrderDate(LocalDate.now());
        order.setArea(new BigDecimal("10.00"));
        order.setTaxId(tax.getTaxId());
        order.setProductId(product.getProductId());
//        order.setTax(tax);
//        order.setProduct(product);
        order.setMaterialCost(new BigDecimal("57.20"));
        order.setTotalLaborCost(new BigDecimal("80.00"));
        order.setTotalTaxCost(new BigDecimal("4.81"));
        order.setTotalOrderCost(new BigDecimal("142.020"));
        
      order =   orderDao.addOrder(order);
      List<Orders> allOrdersOfACustomer = orderDao.getAllOrdersForOneCustomer(order.getCustomerName());
     
      assertNotNull(allOrdersOfACustomer);
        
    }

    /**
     * Test of getOrdersAndProductsForGraph method, of class OrdersDaoInterface.
     */
    @Test
    public void testGetOrdersAndProductsForGraph() {
         Products product = new Products();
        product.setProductType("wood");
        product.setCostPerSquareFoot(new BigDecimal("5.72"));
         product.setLaborCostPerSquareFoot(new BigDecimal("8.90"));
        product = productDao.addOneProduct(product);
        
        Taxes tax = new Taxes();
        tax.setTaxRate(new BigDecimal("3.50"));
        tax.setStateName("utah");
        tax.setStateAbbrevation("ut");
        tax = taxDao.addTax(tax);
        
        Orders order = new Orders();
        order.setCustomerName("jhon");
        order.setOrderDate(LocalDate.now());
        order.setArea(new BigDecimal("10.00"));
        order.setTaxId(tax.getTaxId());
        order.setProductId(product.getProductId());
//        order.setTax(tax);
//        order.setProduct(product);
        order.setMaterialCost(new BigDecimal("57.20"));
        order.setTotalLaborCost(new BigDecimal("80.00"));
        order.setTotalTaxCost(new BigDecimal("4.81"));
        order.setTotalOrderCost(new BigDecimal("142.020"));
        
      order =   orderDao.addOrder(order);
      List<Orders> allOrdersForGraph = orderDao.getOrdersAndProductsForGraph();
     
      assertNotNull(allOrdersForGraph); 
        
    }

    /**
     * Test of getOneProduct method, of class OrdersDaoInterface.
     */
    @Test
    public void testGetOneProduct() {
        
        Products product = new Products();
        product.setProductType("wood");
        product.setCostPerSquareFoot(new BigDecimal("5.72"));
         product.setLaborCostPerSquareFoot(new BigDecimal("8.90"));
        product = productDao.addOneProduct(product);
        
        Taxes tax = new Taxes();
        tax.setTaxRate(new BigDecimal("3.50"));
        tax.setStateName("utah");
        tax.setStateAbbrevation("ut");
        tax = taxDao.addTax(tax);
        
        Orders order = new Orders();
        order.setCustomerName("jhon");
        order.setOrderDate(LocalDate.now());
        order.setArea(new BigDecimal("10.00"));
        order.setTaxId(tax.getTaxId());
        order.setProductId(product.getProductId());
//        order.setTax(tax);
//        order.setProduct(product);
        order.setMaterialCost(new BigDecimal("57.20"));
        order.setTotalLaborCost(new BigDecimal("80.00"));
        order.setTotalTaxCost(new BigDecimal("4.81"));
        order.setTotalOrderCost(new BigDecimal("142.020"));
        
      order =   orderDao.addOrder(order);
      Taxes oneTax = orderDao.getOneTax(order.getOrderId());
      assertNotNull(oneTax);
      
    }

    /**
     * Test of getOneTax method, of class OrdersDaoInterface.
     */
    @Test
    public void testGetOneTax() {
        
         Products product = new Products();
        product.setProductType("wood");
        product.setCostPerSquareFoot(new BigDecimal("5.72"));
         product.setLaborCostPerSquareFoot(new BigDecimal("8.90"));
        product = productDao.addOneProduct(product);
        
        Taxes tax = new Taxes();
        tax.setTaxRate(new BigDecimal("3.50"));
        tax.setStateName("utah");
        tax.setStateAbbrevation("ut");
        tax = taxDao.addTax(tax);
        
        Orders order = new Orders();
        order.setCustomerName("jhon");
        order.setOrderDate(LocalDate.now());
        order.setArea(new BigDecimal("10.00"));
        order.setTaxId(tax.getTaxId());
        order.setProductId(product.getProductId());
//        order.setTax(tax);
//        order.setProduct(product);
        order.setMaterialCost(new BigDecimal("57.20"));
        order.setTotalLaborCost(new BigDecimal("80.00"));
        order.setTotalTaxCost(new BigDecimal("4.81"));
        order.setTotalOrderCost(new BigDecimal("142.020"));
        
      order =   orderDao.addOrder(order);
      Products oneProduct = orderDao.getOneProduct(order.getOrderId());
      assertNotNull(oneProduct);
    }

   
}
