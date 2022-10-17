/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.dao;

import com.javaonly.buildingessentials.dto.Products;
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
public class ProductsDaoImp implements ProductsDaoInterface{

    
     @Autowired
   JdbcTemplate jdbc;
     
    @Override
    public List<Products> getAllProducts() {
        final String GET_ALL_PRODUCTS = "SELECT * FROM product ";
        return jdbc.query(GET_ALL_PRODUCTS, new ProductMapper());
        
    }

    @Override
    public Products getOneProduct(int productId) {
        try{
        final String GET_ONE_PRODUCT = "SELECT * FROM product WHERE productId=?";
        return jdbc.queryForObject(GET_ONE_PRODUCT, new ProductMapper(),productId);
        }catch(DataAccessException ex){
            return null;
        }
         
    }

    @Override
    @Transactional
    public Products addOneProduct(Products product) {
        
        final String ADD_PRODUCT = "INSERT INTO product(productType,costPerSquareFoot,laborCostPerSquareFoot)"+
                                       "VALUES(?,?,?)";
        jdbc.update(ADD_PRODUCT,product.getProductType(),product.getCostPerSquareFoot(),
                product.getLaborCostPerSquareFoot());
        int newProductId = jdbc.queryForObject("SELECT LAST_INSERT_ID()",Integer.class);
        product.setProductId(newProductId);
        return product;
        
    }

    @Override
    public void updateProduct(Products product) {
        
        final String UPDATE_PRODUCT = "UPDATE product set productType=?,costPerSquareFoot=?,laborCostPerSquareFoot=? WHERE productId=?";
        jdbc.update(UPDATE_PRODUCT,product.getProductType(),product.getCostPerSquareFoot(),
                                    product.getLaborCostPerSquareFoot(),product.getProductId());
       
    }

    @Override
    @Transactional
    public void deleteProduct(int productId) {
        final String DELETE_PROD_FROM_ORDERS = "DELETE FROM orders Where productId = ?";
        jdbc.update(DELETE_PROD_FROM_ORDERS,productId);
        
        final String DELETE_PRODUCT = "DELETE FROM product WHERE productId=?";
        jdbc.update(DELETE_PRODUCT,productId);
        
         //To change body of generated methods, choose Tools | Templates.
    }
    
    public static final class ProductMapper implements RowMapper<Products>{
        @Override
        public Products mapRow(ResultSet rs,int index)throws SQLException{
          Products product= new Products();
          product.setProductId(rs.getInt("productId"));
          product.setProductType(rs.getString("productType"));
          product.setCostPerSquareFoot(rs.getBigDecimal("costPerSquareFoot"));
          product.setLaborCostPerSquareFoot(rs.getBigDecimal("laborCostPerSquareFoot"));
          return product;
        } 
        
    }
    
}
