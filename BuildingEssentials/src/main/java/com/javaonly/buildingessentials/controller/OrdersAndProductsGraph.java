/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.controller;

import com.javaonly.buildingessentials.dto.Orders;
import com.javaonly.buildingessentials.service.OrderServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Prathima
 */

@RestController
@RequestMapping("/ordersGraph")
public class OrdersAndProductsGraph {
    
    @Autowired
    private final OrderServiceInterface orderService;
    

     @Autowired
   public OrdersAndProductsGraph(OrderServiceInterface orderService){
       this.orderService = orderService;
   }
   
   //Returns a list of all games(just for quick check)
   @GetMapping
   public List<Orders> all(){
       return orderService.getAllForGraph();
       
   }
    
}
