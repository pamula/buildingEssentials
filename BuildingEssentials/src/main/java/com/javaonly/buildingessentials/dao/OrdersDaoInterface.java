/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.dao;

import com.javaonly.buildingessentials.dto.Orders;
import com.javaonly.buildingessentials.dto.Products;
import com.javaonly.buildingessentials.dto.Taxes;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface OrdersDaoInterface {
    Orders addOrder(Orders order);
    List<Orders> getAllOrders();
    Orders getOneOrder(int orderId);
    void updateOrder(Orders order);
    void deleteOrder(int orderId);
    
    //get all orders for one specific date
    //get all orders for one name
    List<Orders> getAllOrdersForOneDate(String date);
    List<Orders> getAllOrdersForOneCustomer(String customerName);
    
    //get orders and products for graph
    List<Orders> getOrdersAndProductsForGraph();
    
    Products getOneProduct(int id);
    Taxes getOneTax(int id);
}
