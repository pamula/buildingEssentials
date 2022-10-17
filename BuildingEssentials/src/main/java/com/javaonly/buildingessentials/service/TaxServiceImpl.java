/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.service;

import com.javaonly.buildingessentials.dao.OrdersDaoInterface;
import com.javaonly.buildingessentials.dao.ProductsDaoInterface;
import com.javaonly.buildingessentials.dao.TaxesDaoInterface;
import com.javaonly.buildingessentials.dto.Taxes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prathima
 */

@Repository
public class TaxServiceImpl implements TaxServiceInterface{
    @Autowired
    ProductsDaoInterface productDao;
    
    @Autowired
    TaxesDaoInterface taxDao;
    
    @Autowired
    OrdersDaoInterface orderDao;

    @Override
    public Taxes addTax(Taxes tax) {
      return  taxDao.addTax(tax);
    }

    @Override
    public List<Taxes> getAllTaxes() {
        return taxDao.getAllTaxes();
    }

    @Override
    public Taxes getOneTax(int taxId) {
       return taxDao.getOneTax(taxId);
    }

    @Override
    public void updateTax(Taxes tax) {
        taxDao.updateTax(tax);
    }
    @Override
    public void deleteTax(int taxId) {
        taxDao.deleteTax(taxId);
    }
    
}
