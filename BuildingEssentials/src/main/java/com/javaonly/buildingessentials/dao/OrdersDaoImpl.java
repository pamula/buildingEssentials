/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.dao;

import com.javaonly.buildingessentials.dao.ProductsDaoImp.ProductMapper;
import com.javaonly.buildingessentials.dao.TaxesDaoImpl.TaxMapper;
import com.javaonly.buildingessentials.dto.Orders;
import com.javaonly.buildingessentials.dto.Products;
import com.javaonly.buildingessentials.dto.Taxes;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Prathima
 */
@Repository
public class OrdersDaoImpl implements OrdersDaoInterface{

    
     @Autowired
   JdbcTemplate jdbc;
    
    @Override
    public List<Orders> getAllOrders() {
        
        final String GET_ALL_ORDERS = "SELECT * FROM orders";
        return jdbc.query(GET_ALL_ORDERS, new OrderMapper());
        
    }
    
    @Override
    @Transactional
    public Orders addOrder(Orders order) {
       
        final String ADD_ONE_ORDER = "INSERT INTO orders(customerName,orderDate,taxId,productId,area,materialCost,totalLaborCost,totalTaxCost,totalOrderCost) VALUES(?,?,?,?,?,?,?,?,?)";
         


jdbc.update(ADD_ONE_ORDER,
                order.getCustomerName(),
                order.getOrderDate(),
               // order.getUserDate(),
                order.getTaxId(),
                order.getProductId(),
                   order.getArea(),
                   order.getMaterialCost(),
                   order.getTotalLaborCost(),
                   order.getTotalTaxCost(),order.getTotalOrderCost());
       
int newOrderId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        order.setOrderId(newOrderId);
        return order;
          
    }

    @Override
    public Orders getOneOrder(int orderId) {
        try{
        final String GET_ONE_ORDER = "SELECT * FROM orders WHERE orderId =?";
        return jdbc.queryForObject(GET_ONE_ORDER, new OrderMapper(),orderId);
        }catch(DataAccessException ex){
            return null;
        }
         
    }

    @Override
    public void updateOrder(Orders order) {
        
        final String UPDATE_ORDER = "UPDATE orders set customerName=?,orderDate=?,taxId=?,productId=?,area=?,materialCost=?,totalLaborCost=?,totalTaxCost=?,totalOrderCost=? WHERE orderId=?;"; 
                            
        jdbc.update(UPDATE_ORDER,
                order.getCustomerName(),
                order.getOrderDate(),
                //order.getUserDate(),
                order.getTaxId(),
                order.getProductId(),
                order.getArea(),
                order.getMaterialCost(),
                order.getTotalLaborCost(),
                order.getTotalTaxCost(),
                order.getTotalOrderCost(),
                order.getOrderId());
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void deleteOrder(int orderId) {
        final String DELETE_ORDER = "DELETE FROM orders WHERE orderId =?";
        jdbc.update(DELETE_ORDER,orderId);
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Orders> getAllOrdersForOneDate(String date) {
        final String GET_ORDERS_FOR_GIVENDATE = "SELECT * FROM orders WHERE orderDate=?";
        return jdbc.query(GET_ORDERS_FOR_GIVENDATE, new OrderMapper(),date);
        
    }

    @Override
    public List<Orders> getAllOrdersForOneCustomer(String customerName) {
        final String GET_ALL_ORDERS_OF_A_CUSTOMER = "SELECT * FROM orders WHERE customerName=?";
        return jdbc.query(GET_ALL_ORDERS_OF_A_CUSTOMER, new OrderMapper(),customerName);
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Products getOneProduct(int id) {
        final String GET_ONE_PRODUCT = "SELECT * FROM product JOIN orders ON "
                + "orders.productId = product.productId WHERE orders.orderId=?";
        return jdbc.queryForObject(GET_ONE_PRODUCT, new ProductMapper(),id);
        
    }

    @Override
    public Taxes getOneTax(int id) {
        final String GET_ONE_TAX = "SELECT * FROM tax JOIN orders ON orders.taxId = tax.taxId WHERE orders.orderId=?";
        return jdbc.queryForObject(GET_ONE_TAX, new TaxMapper(),id);
    }

    @Override
    public List<Orders> getOrdersAndProductsForGraph() {
        final String allOrdersAndProductsForGraph = "select * from orders join product on orders.productId = product.productId;";
        return jdbc.query(allOrdersAndProductsForGraph, new OrderMapper());
    }
    
    
    public static final class OrderMapper implements RowMapper<Orders>{
       
        @Override
        public Orders mapRow(ResultSet rs,int index) throws SQLException{ 
        
            Orders order = new Orders();
         order.setOrderId(rs.getInt("orderId"));
         order.setCustomerName(rs.getString("customerName"));
         order.setOrderDate(rs.getDate("orderDate").toLocalDate());
         order.setTaxId(rs.getInt("taxId"));
         order.setProductId(rs.getInt("productId"));
         order.setArea(rs.getBigDecimal("area"));
         order.setMaterialCost(rs.getBigDecimal("materialCost"));
         order.setTotalLaborCost(rs.getBigDecimal("totalLaborCost"));
         order.setTotalTaxCost(rs.getBigDecimal("totalTaxCost"));
         order.setTotalOrderCost(rs.getBigDecimal("totalOrderCost"));
         return order;
            
    }
    }
    
}
