/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.service;

import com.javaonly.buildingessentials.dao.OrdersDaoInterface;
import com.javaonly.buildingessentials.dao.ProductsDaoInterface;
import com.javaonly.buildingessentials.dao.TaxesDaoInterface;
import com.javaonly.buildingessentials.dto.Products;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prathima
 */

@Repository
public class ProductServiceImpl implements ProductServiceInterface{

    
    @Autowired
    ProductsDaoInterface productDao;
    
    @Autowired
    TaxesDaoInterface taxDao;
    
    @Autowired
    OrdersDaoInterface orderDao;
    
    
    @Override
    public Products getOneProductById(int productId) {
      return  productDao.getOneProduct(productId);
    }

    @Override
    public List<Products> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public Products addProduct(Products product) {
         return productDao.addOneProduct(product);
    }

    @Override
    public void updateProduct(Products product) {
        productDao.updateProduct(product);
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProduct(int productId) {
        productDao.deleteProduct(productId);
        //To change body of generated methods, choose Tools | Templates.
    }
    
}
