/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.dao;

import com.javaonly.buildingessentials.dto.Products;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface ProductsDaoInterface {
    List<Products> getAllProducts();
    Products getOneProduct(int productId);
    Products addOneProduct(Products product);
    void updateProduct(Products product);
    void deleteProduct(int productId);
    
    
}
