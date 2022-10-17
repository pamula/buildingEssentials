/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.service;

import com.javaonly.buildingessentials.dto.Orders;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface OrderServiceInterface {
     Orders addOrder(Orders order);
    List<Orders> getAllOrders();
    Orders getOneOrder(int orderId);
    void updateOrder(Orders order);
    void deleteOrder(int orderId);
    
    List<Orders>getAllForGraph();
    
    List<Orders> getOrdersForADate(String orderDate);
    List<Orders> getOrdersForACustomer(String customerName);
    
}
