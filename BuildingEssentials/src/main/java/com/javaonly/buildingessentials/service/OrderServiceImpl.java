/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.service;

import com.javaonly.buildingessentials.dao.OrdersDaoInterface;
import com.javaonly.buildingessentials.dao.ProductsDaoInterface;
import com.javaonly.buildingessentials.dao.TaxesDaoInterface;
import com.javaonly.buildingessentials.dto.Orders;
import com.javaonly.buildingessentials.dto.Products;
import com.javaonly.buildingessentials.dto.Taxes;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prathima
 */
@Repository
public class OrderServiceImpl implements OrderServiceInterface {
    
    @Autowired
    ProductsDaoInterface productDao;
    
    @Autowired
    TaxesDaoInterface taxDao;
    
    @Autowired
    OrdersDaoInterface orderDao;

    @Override
    public Orders addOrder(Orders order) {
         /*
     MaterialCost = (Area * CostPerSquareFoot)
LaborCost = (Area * LaborCostPerSquareFoot)
totalTax = (MaterialCost + LaborCost) * (TaxRateofstate/100)

TotalCost = (MaterialCost + LaborCost + totalTax)
    */
     
         
         return orderDao.addOrder(order);
        
    }
    @Override
    public List<Orders> getAllOrders() {
        List<Orders> orders = orderDao.getAllOrders();
        orderAndTax(orders);
        orderAndProduct(orders);
        return orders;
    }
private void orderAndProduct(List<Orders> orders){
    
    List<Products> products = productDao.getAllProducts();
    for(Orders allOrders: orders){
        allOrders.setProduct(orderDao.getOneProduct(allOrders.getOrderId()));
    }
    
}
private void orderAndTax(List<Orders> orders){
    List<Taxes> taxes = taxDao.getAllTaxes();
    for(Orders allOrders: orders){
        allOrders.setTax(orderDao.getOneTax(allOrders.getOrderId()));
    }
    
}
    @Override
    public Orders getOneOrder(int orderId) {
        return orderDao.getOneOrder(orderId);
    }

    @Override
    public void updateOrder(Orders order) {
        
        Products oneProductForOrderId = orderDao.getOneProduct(order.getOrderId());
         Taxes oneTaxForOrderId = orderDao.getOneTax(order.getOrderId());
         
         BigDecimal costPerSqFt = oneProductForOrderId.getCostPerSquareFoot();
         BigDecimal mtrlCost = order.getArea().multiply(costPerSqFt);
         
         BigDecimal lbrCostPerSqFt = oneProductForOrderId.getLaborCostPerSquareFoot();
         BigDecimal lbrCost = order.getArea().multiply(lbrCostPerSqFt);
         BigDecimal value = new BigDecimal("100");
         
         BigDecimal totalTx = (mtrlCost.add(lbrCost)).multiply((oneTaxForOrderId.getTaxRate().divide(value)));
        BigDecimal totalCst = (mtrlCost.add(lbrCost)).add(totalTx);
        
        //set all calculated values to order
        order.setMaterialCost(mtrlCost);
        order.setTotalLaborCost(lbrCost);
        order.setTotalTaxCost(totalTx );
        order.setTotalOrderCost(totalCst);
        
        orderDao.updateOrder(order);
    }
    @Override
    public void deleteOrder(int orderId) {
        orderDao.deleteOrder(orderId);
    }
    @Override
    public List<Orders> getOrdersForADate(String orderDate) {
         List<Orders> orders = orderDao.getAllOrdersForOneDate(orderDate);
         orderAndTax(orders);
        orderAndProduct(orders);
        return orders;
    }
    @Override
    public List<Orders> getOrdersForACustomer(String customerName) {
         List<Orders> orders = orderDao.getAllOrdersForOneCustomer(customerName);
         orderAndTax(orders);
        orderAndProduct(orders);
        return orders;
    }

    @Override
    public List<Orders> getAllForGraph() {
        List<Orders> orders = orderDao.getOrdersAndProductsForGraph();
         orderAndTax(orders);
        orderAndProduct(orders);
        return orders;
        //return orderDao.getOrdersAndProductsForGraph();
    }
}
