/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.controller;


import com.javaonly.buildingessentials.dao.OrdersDaoInterface;
import com.javaonly.buildingessentials.dto.Orders;
import com.javaonly.buildingessentials.dto.Products;
import com.javaonly.buildingessentials.dto.Taxes;
import com.javaonly.buildingessentials.service.OrderServiceInterface;
import com.javaonly.buildingessentials.service.ProductServiceInterface;
import com.javaonly.buildingessentials.service.TaxServiceInterface;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/**
 *
 * @author Prathima
 */

@Controller
public class OrderController {
    
    @Autowired
    OrderServiceInterface orderService;
    
    @Autowired
    ProductServiceInterface productService;
    
    @Autowired
    TaxServiceInterface taxService;
    
   @Autowired
   OrdersDaoInterface orderDao;
   
   
    Set<ConstraintViolation<Orders>> violations = new HashSet<>();


    
    @GetMapping(value ={"","/","/index"})
    public String getOrders(Model model){
        List<Orders> orders = orderService.getAllOrders();
        List<Products> products = productService.getAllProducts();
        List<Taxes> taxes = taxService.getAllTaxes();
        model.addAttribute("orders",orders);
        model.addAttribute("taxes",taxes);
        model.addAttribute("products",products);
        
        return "index";
        
    }
    
    
     @GetMapping("orders")
    public String getAllOrders(Model model){
        List<Orders> orders = orderService.getAllOrders();
        List<Products> products = productService.getAllProducts();
        List<Taxes> taxes = taxService.getAllTaxes();
        model.addAttribute("orders",orders);
        model.addAttribute("taxes",taxes);
        model.addAttribute("products",products);
        
         model.addAttribute("errors", violations);
        
        return "orders";
        
    }
    
    
    @GetMapping("graph")
    public String getForGraph(Model model){
        List<Orders> orders = orderService.getAllForGraph();
        List<Products> products = productService.getAllProducts();
        List<Taxes> taxes = taxService.getAllTaxes();
        model.addAttribute("orders",orders);
        model.addAttribute("taxes",taxes);
        model.addAttribute("products",products);
        
        return "graph";
        
    }
    
     @PostMapping("addOrder")
    public String addOrder(@Valid Orders order,BindingResult result, HttpServletRequest request){
       
        String cstmrName = request.getParameter("customerName");
       
        String date = request.getParameter("orderedDate");
      
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate afterFormatted =LocalDate.parse( date,formatter);
        
         String taxId = request.getParameter("taxId");
        String productId = request.getParameter("productId");
       BigDecimal area =new BigDecimal(request.getParameter("area"));
    
      Orders oneOrder = new Orders();
     oneOrder.setCustomerName(cstmrName);
     oneOrder.setOrderDate(afterFormatted);
    
 oneOrder.setTaxId(Integer.parseInt(taxId));
     Taxes taxForOrder = taxService.getOneTax(oneOrder.getTaxId());
     oneOrder.setTax(taxForOrder);
     oneOrder.setProductId(Integer.parseInt(productId));
     
     Products productForOrder = productService.getOneProductById(oneOrder.getProductId());
     oneOrder.setProduct(productForOrder);
     oneOrder.setProductType(productForOrder.getProductType());
     oneOrder.setArea((area));
      
     BigDecimal costPerSqFt = productForOrder.getCostPerSquareFoot();
      BigDecimal lbrCostPerSqFt = productForOrder.getLaborCostPerSquareFoot();
      
      BigDecimal mtrlCost = oneOrder.getArea().multiply(costPerSqFt);
      
      BigDecimal lbrCost = oneOrder.getArea().multiply(lbrCostPerSqFt);
         BigDecimal value = new BigDecimal("100");
         
         BigDecimal totalTx = (mtrlCost.add(lbrCost)).multiply((taxForOrder.getTaxRate().divide(value)));
        BigDecimal totalCst = (mtrlCost.add(lbrCost)).add(totalTx);
     
        //set all calculated values to order
        oneOrder.setMaterialCost(mtrlCost);
        oneOrder.setTotalLaborCost(lbrCost);
        oneOrder.setTotalTaxCost(totalTx );
        oneOrder.setTotalOrderCost(totalCst);
       
         Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
            violations = validate.validate(oneOrder);

            if(violations.isEmpty()) {
             orderService.addOrder(oneOrder);
                  }
    
     return "redirect:/orders";
     
        
    }
    
    
     @PostMapping("editOrder")
     public String editOrder(HttpServletRequest request){
      int id = Integer.parseInt(request.getParameter("orderId"));
      Orders order = orderService.getOneOrder(id);
      String custmName = request.getParameter("customerName");
      String date = request.getParameter("orderedDate");
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate afterFormatted =LocalDate.parse( date,formatter);
         BigDecimal area =new BigDecimal(request.getParameter("area"));
        order.setCustomerName(custmName);
        order.setOrderDate(afterFormatted);
        order.setTaxId(Integer.parseInt(request.getParameter("taxId")));
        order.setProductId(Integer.parseInt(request.getParameter("taxId")));
        order.setArea(area);
        Taxes taxForOrder = taxService.getOneTax(order.getTaxId());
     order.setTax(taxForOrder);
     Products productForOrder = productService.getOneProductById(order.getProductId());
     order.setProduct(productForOrder);
     order.setProductType(productForOrder.getProductType());
     
     BigDecimal costPerSqFt = productForOrder.getCostPerSquareFoot();
      BigDecimal lbrCostPerSqFt = productForOrder.getLaborCostPerSquareFoot();
      
      BigDecimal mtrlCost = order.getArea().multiply(costPerSqFt);
      
      BigDecimal lbrCost = order.getArea().multiply(lbrCostPerSqFt);
         BigDecimal value = new BigDecimal("100");
         
         BigDecimal totalTx = (mtrlCost.add(lbrCost)).multiply((taxForOrder.getTaxRate().divide(value)));
        BigDecimal totalCst = (mtrlCost.add(lbrCost)).add(totalTx);
     
        //set all calculated values to order
        order.setMaterialCost(mtrlCost);
        order.setTotalLaborCost(lbrCost);
        order.setTotalTaxCost(totalTx );
        order.setTotalOrderCost(totalCst);
        
        orderService.updateOrder(order);
     
      return "redirect:/orders";
   } 
    
     @GetMapping("ordersByDate")
    public String getAllOrdersByDate(HttpServletRequest request,Model model){
        String ordersForAdATE = request.getParameter("userDate");
        List<Orders> orders = orderService.getOrdersForADate(ordersForAdATE);
        List<Products> products = productService.getAllProducts();
        List<Taxes> taxes = taxService.getAllTaxes();
        model.addAttribute("orders",orders);
        model.addAttribute("taxes",taxes);
        model.addAttribute("products",products);
        
        return "ordersByDate";
        
    }
    
     @GetMapping("ordersByCustomer")
    public String getAllOrdersByCustomer(HttpServletRequest request,Model model){
        String ordersForACustomer = request.getParameter("customerName");
        List<Orders> orders = orderService.getOrdersForACustomer(ordersForACustomer);
        List<Products> products = productService.getAllProducts();
        List<Taxes> taxes = taxService.getAllTaxes();
        model.addAttribute("orders",orders);
        model.addAttribute("taxes",taxes);
        model.addAttribute("products",products);
        
        return "ordersByCustomer";
        
    }
    
    
    
     @GetMapping("orderDetails")
     public String orderDetails(Integer orderId,Model model){
         Orders order = orderService.getOneOrder(orderId);
         Products product = productService.getOneProductById(order.getProductId());
         Taxes tax = taxService.getOneTax(order.getTaxId());
         
         model.addAttribute("orders",order);
        model.addAttribute("taxes",tax);
        model.addAttribute("products",product);
        
        return "orderDetails";
     }
    
     @GetMapping("deleteOrder")
     public String deleteOrder(HttpServletRequest request){
         int id = Integer.parseInt(request.getParameter("orderId"));
         orderService.deleteOrder(id);
         return "redirect:/orders";
     }
     
    
     @GetMapping("editOrder")
     public String editOneOrder(Integer orderId, Model model){
          Orders order = orderService.getOneOrder(orderId);
         Products product = productService.getOneProductById(order.getProductId());
         Taxes tax = taxService.getOneTax(order.getTaxId());
         
         List<Products> products = productService.getAllProducts();
        List<Taxes> taxes = taxService.getAllTaxes();
        
         order.setProduct(product);
         order.setTax(tax);
         
         model.addAttribute("orders",order);
            
            model.addAttribute("products",products);
            model.addAttribute("taxes",taxes);
          
            return "editOrder";
         
         
          }
     
    
    
    
}
